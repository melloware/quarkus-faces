App = {
    init: function () {
        this.wrapper = $(document.body).children('.layout-wrapper');
        this.topbar = this.wrapper.children('.layout-topbar');
        this.topbarMenu = this.topbar.find('> form > .topbar-menu');
        this.sidebar = this.wrapper.children('.layout-sidebar');
        this.menu = this.sidebar.children('.layout-menu');
        this.menuLinks = this.menu.find('a');
        this.mask = this.wrapper.children('.layout-mask');
        this.menuButton = this.topbar.children('.menu-button');
        this.configurator = this.wrapper.children('.layout-config');
        this.configuratorButton = $('#layout-config-button');
        this.configuratorCloseButton = $('#layout-config-close-button');
        this.filterPanel = $('.layout-sidebar-filter-panel');
        this.news = this.wrapper.children('.layout-news');
        this.activeSubmenus = [];

        this._bindEvents();
        this._bindNews();

        this.restoreMenu();
        Storage.restoreSettings();
    },

    _bindEvents: function () {
        let $this = this;

        this.topbarMenu.find('> .topbar-submenu > a').off('click').on('click', function () {
            let item = $(this).parent();

            item.siblings('.topbar-submenu-active').removeClass('topbar-submenu-active');

            if (item.hasClass('topbar-submenu-active'))
                $this.hideTopbarSubmenu(item);
            else
                $this.showTopbarSubmenu(item);
        });

        this.topbarMenu.find('.connected-overlay-in a').off('click').on('click', function () {
            $this.hideTopbarSubmenu($this.topbarMenu.children('.topbar-submenu-active'));
        });

        this.menuLinks.off('click').on('click', function () {
            let link = $(this);

            if (link.hasClass('submenu-link')) {
                if (link.hasClass('submenu-link-active')) {
                    $this.activeSubmenus = $.grep($this.activeSubmenus, function (val) {
                        return val !== link.attr('id');
                    });
                    link.removeClass('submenu-link-active').next('.submenu').slideUp('fast');
                } else {
                    $this.activeSubmenus.push(link.attr('id'));
                    link.addClass('submenu-link-active').next('.submenu').slideDown('fast');
                }

                sessionStorage.setItem('active_submenus', $this.activeSubmenus.join(','));
            } else {
                link.addClass('router-link-active');
            }
        });

        this.menu.off('scroll').on('scroll', function () {
            sessionStorage.setItem('scroll_position', $this.menu.scrollTop());
        });

        $(document).off('click.showcase').on('click.showcase', function (event) {
            if (!$.contains($this.topbarMenu.get(0), event.target)) {
                $this.hideTopbarSubmenu($this.topbarMenu.children('.topbar-submenu-active'));
            }

            if ($this.sidebar.hasClass('active') && !$.contains($this.sidebar.get(0), event.target) && !$this.isMenuButton(event.target)) {
                $this.sidebar.removeClass('active');
                $this.mask.removeClass('layout-mask-active');
            }

            if ($this.configurator.hasClass('layout-config-active') && !$.contains($this.configurator.get(0), event.target)) {
                $this.configurator.removeClass('layout-config-active');
            }
        });

        this.menuButton.off('click').on('click', function () {
            $this.sidebar.addClass('active');
            $this.mask.addClass('layout-mask-active');
        });

        this.configuratorButton.off('click').on('click', function () {
            $this.configurator.toggleClass('layout-config-active');
        });

        this.configuratorCloseButton.off('click').on('click', function () {
            $this.configurator.removeClass('layout-config-active');
        });

        this.filterPanel.off('click.showcase', '.ui-autocomplete-item')
            .on('click.showcase', '.ui-autocomplete-item', function (e) {
                if (!$this.isLinkClicked) {
                    $this.isLinkClicked = true;

                    let link = $(this).find('a:first');
                    if (link) {
                        let url = new URL(link[0].href);
                        let menuItem = $this.menu.find('a[href*="' + url.pathname + '"]');
                        if (menuItem.length) {
                            let scroll_position = '' + (menuItem[0].offsetTop - $this.menu[0].offsetTop);

                            sessionStorage.setItem('scroll_position', scroll_position);
                        }

                        link.trigger('click');

                        let href = link.attr('href');
                        if (href && href !== '#') {
                            window.location.href = href;
                        }
                    }
                }
                $this.isLinkClicked = false;
                e.preventDefault();
            });
    },

    hideTopbarSubmenu: function (item) {
        let submenu = item.children('ul');
        submenu.addClass('connected-overlay-out');

        setTimeout(function () {
            item.removeClass('topbar-submenu-active');
            submenu.removeClass('connected-overlay-out');
        }, 100);
    },

    showTopbarSubmenu: function (item) {
        item.addClass('topbar-submenu-active');
    },

    changeTheme: function (theme, dark) {
        PrimeFaces.changeTheme(theme);

        if (dark)
            $('#homepage-intro').addClass('introduction-dark');
        else
            $('#homepage-intro').removeClass('introduction-dark');
    },

    updateInputStyle: function (value) {
        if (value === 'filled')
            this.wrapper.addClass('ui-input-filled');
        else
            this.wrapper.removeClass('ui-input-filled');
    },

    isMenuButton: function (element) {
        return $.contains(this.menuButton.get(0), element) || this.menuButton.is(element);
    },

    restoreMenu: function () {
        let activeRouteLink = this.menuLinks.filter('[href^="' + window.location.pathname + '"]');
        if (activeRouteLink.length) {
            activeRouteLink.addClass('router-link-active');
        }

        let activeSubmenus = sessionStorage.getItem('active_submenus');
        if (activeSubmenus) {
            this.activeSubmenus = activeSubmenus.split(',');
            this.activeSubmenus.forEach(function (id) {
                $('#' + id).addClass('submenu-link-active').next().show();
            });
        }

        let scrollPosition = sessionStorage.getItem('scroll_position');
        if (scrollPosition) {
            this.menu.scrollTop(parseInt(scrollPosition));
        }
    },

    onSearchClick: function (event, id) {
        if (id && this.activeSubmenus.indexOf(id) === -1) {
            this.activeSubmenus.push(id);
            $('#' + id).next().show();
            sessionStorage.setItem('active_submenus', this.activeSubmenus.join(','));
        }
    },

    _bindNews: function () {
        if (this.news && this.news.length > 0) {
            let $this = this;
            let closeButton = this.news.find('.layout-news-close');
            closeButton.off('click.news').on('click.news', function () {
                $this.wrapper.removeClass('layout-news-active');
                $this.news.hide();

                Storage.saveSettings(false);
            });
        }
    },

    changeNews: function (active) {
        if (this.news && this.news.length > 0) {
            if (active)
                this.wrapper.addClass('layout-news-active');
            else
                this.wrapper.removeClass('layout-news-active');
        }
    }
}

let Storage = {
    storageKey: 'primefaces',
    saveSettings: function (newsActive) {
        let now = new Date();
        let item = {
            settings: {
                newsActive: newsActive
            },
            expiry: now.getTime() + 604800000
        }
        localStorage.setItem(this.storageKey, JSON.stringify(item));
    },
    restoreSettings: function () {
        let itemString = localStorage.getItem(this.storageKey);
        if (itemString) {
            let item = JSON.parse(itemString);
            if (!this.isStorageExpired()) {
                // News
                App.changeNews(item.settings.newsActive);
            }
        }
    },
    isStorageExpired: function () {
        let itemString = localStorage.getItem(this.storageKey);
        if (!itemString) {
            return true;
        }
        let item = JSON.parse(itemString);
        let now = new Date();

        if (now.getTime() > item.expiry) {
            localStorage.removeItem(this.storageKey);
            return true;
        }

        return false;
    }
}

App.init();