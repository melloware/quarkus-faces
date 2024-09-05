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
        var $this = this;

        this.topbarMenu.find('> .topbar-submenu > a').off('click').on('click', function () {
            var item = $(this).parent();

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
            var link = $(this);

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

                    var link = $(this).find('a:first');
                    if (link) {
                        var url = new URL(link[0].href);
                        var menuItem = $this.menu.find('a[href*="' + url.pathname + '"]');
                        if (menuItem.length) {
                            var scroll_position = menuItem[0].offsetTop - $this.menu[0].offsetTop;

                            sessionStorage.setItem('scroll_position', scroll_position);
                        }

                        link.trigger('click');

                        var href = link.attr('href');
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
        var submenu = item.children('ul');
        submenu.addClass('connected-overlay-out');

        setTimeout(function () {
            item.removeClass('topbar-submenu-active'),
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
        var activeRouteLink = this.menuLinks.filter('[href^="' + window.location.pathname + '"]');
        if (activeRouteLink.length) {
            activeRouteLink.addClass('router-link-active');
        }

        var activeSubmenus = sessionStorage.getItem('active_submenus');
        if (activeSubmenus) {
            this.activeSubmenus = activeSubmenus.split(',');
            this.activeSubmenus.forEach(function (id) {
                $('#' + id).addClass('submenu-link-active').next().show();
            });
        }

        var scrollPosition = sessionStorage.getItem('scroll_position');
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
            var $this = this;
            var closeButton = this.news.find('.layout-news-close');
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

var Storage = {
    storageKey: 'primefaces',
    saveSettings: function (newsActive) {
        var now = new Date();
        var item = {
            settings: {
                newsActive: newsActive
            },
            expiry: now.getTime() + 604800000
        }
        localStorage.setItem(this.storageKey, JSON.stringify(item));
    },
    restoreSettings: function () {
        var itemString = localStorage.getItem(this.storageKey);
        if (itemString) {
            var item = JSON.parse(itemString);
            if (!this.isStorageExpired()) {
                // News
                App.changeNews(item.settings.newsActive);
            }
        }
    },
    isStorageExpired: function () {
        var itemString = localStorage.getItem(this.storageKey);
        if (!itemString) {
            return true;
        }
        var item = JSON.parse(itemString);
        var now = new Date();

        if (now.getTime() > item.expiry) {
            localStorage.removeItem(this.storageKey);
            return true;
        }

        return false;
    }
}

App.init();

if (PrimeFaces.widget.SelectOneMenu) {
  /**
   * Renders panel content based on hidden select.
   * @private
   * @param {boolean} initContentsAndBindItemEvents `true` to call {@link initContents} and {@link bindItemEvents}
   * after rendering, `false` otherwise.
   */
  PrimeFaces.widget.SelectOneMenu.prototype.renderPanelContentFromHiddenSelect = function(initContentsAndBindItemEvents) {
    if (this.cfg.renderPanelContentOnClient && this.itemsWrapper.children().length === 0) {
      var panelContent = '<div id="' + this.id + '_items" class="ui-selectonemenu-items ui-widget-content ui-widget ui-corner-all ui-helper-reset" role="listbox">';
      panelContent += this.renderSelectItems(this.input);
      panelContent += '</div>';

      this.itemsWrapper.append(panelContent);

      if (initContentsAndBindItemEvents) {
        this.initContents();
        this.bindItemEvents();
      }
    }
  };

  /**
   * Renders panel HTML-code for all select items.
   * @private
   * @param {JQuery} parentItem A parent item (select, optgroup) for which to render HTML code.
   * @param {boolean} [isGrouped] Indicated whether the elements of the parent item should be marked as grouped.
   * @return {string} The rendered HTML string.
   */
  PrimeFaces.widget.SelectOneMenu.prototype.renderSelectItems = function(parentItem, isGrouped) {
    var $this = this;
    var content = "";
    isGrouped = isGrouped || false;

    var opts = parentItem.children("option, optgroup");
    var hasOptgroup = opts.filter("optgroup").length > 0;

    if (!hasOptgroup && !isGrouped) {
      content += '<ul role="group" class="ui-selectonemenu-list ui-widget-content ui-widget ui-corner-all ui-helper-reset">';
    }

    content += opts.map(function(index, element) {
      return $this.renderSelectItem(element, isGrouped);
    }).get().join('');

    if (!hasOptgroup && !isGrouped) {
      content += '</ul>';
    }

    return content;
  };

  /**
   * Renders panel HTML code for one select item (group).
   * @private
   * @param {JQuery} item An option (group) for which to render HTML code.
   * @param {boolean} [isGrouped] Indicates whether the item is part of a group.
   * @return {string} The rendered HTML string.
   */
  PrimeFaces.widget.SelectOneMenu.prototype.renderSelectItem = function(item, isGrouped) {
    var $item = $(item);
    var isOptgroup = item.tagName === "OPTGROUP";
    var id = PrimeFaces.uuid();
    var escape = $item.data("escape");
    var label = isOptgroup ? $item.attr("label") : $item.text();

    if (escape) {
      label = isOptgroup ? $("<div>").text(label).html() : $item.html();
      label = label === "&nbsp;" ? " " : label;
    }

    var cssClass = isOptgroup ? "ui-selectonemenu-item-group ui-corner-all" :
      "ui-selectonemenu-item ui-selectonemenu-list-item ui-corner-all" +
      (isGrouped ? " ui-selectonemenu-item-group-children" : "") +
      ($item.data("noselection-option") ? " ui-noselection-option" : "");

    var content = isOptgroup ? '<ul role="group" class="ui-selectonemenu-list ui-widget-content ui-widget ui-corner-all ui-helper-reset" aria-labelledby="' + id + '">' : '';

    content += '<li id="' + id + '" class="' + cssClass + '" tabindex="-1" role="' + (isOptgroup ? "presentation" : "option") + '"';

    var title = $item.data("title");
    if (title) {
      content += ' title="' + PrimeFaces.escapeHTML(title) + '"';
    }
    if ($item.is(':disabled')) {
      content += ' disabled';
    }

    var dataLabel = escape ? label.replaceAll('"', '&quot;') : PrimeFaces.escapeHTML(label, true);
    content += ' data-label="' + dataLabel + '">' + label + '</li>';

    if (isOptgroup) {
      content += this.renderSelectItems($item, true) + '</ul>';
    }

    return content;
  };
  
   /**
     * Highlights the next option after the currently highlighted option in the overlay panel.
     * @private
     * @param {JQuery.TriggeredEvent} event The event of the keypress.
     */
    PrimeFaces.widget.SelectOneMenu.prototype.highlightNext = function(event) {
        // Get the currently active item
        var activeItem = this.getActiveItem();
        
        // Check if the panel is hidden
        var isHidden = this.panel.is(':hidden');
        
        // Create a selector for the next valid option
        // If panel is hidden, select the first option, otherwise select the first visible option
        var selector = '[role="option"]:not(.ui-state-disabled)' + (isHidden ? ':first' : ':visible:first');
        
        // Try to find the next valid option after the active item
        var next = activeItem.nextAll(selector);

        if (next.length === 0) {
            var index = this.items.index(activeItem) + 1;
            if (index >= 0) {
                next = this.items.eq(index);
            }
        }
    
        if(event.altKey) {
            this.show();
        }
        else if(next.length === 1) {
            if(this.panel.is(':hidden')) {
                this.selectItem(next);
            }
            else {
                this.highlightItem(next);
                PrimeFaces.scrollInView(this.itemsWrapper, next);
            }
            this.changeAriaValue(next);
        }

        event.preventDefault();
    };

    /**
     * Highlights the previous option before the currently highlighted option in the overlay panel.
     * @private
     * @param {JQuery.TriggeredEvent} event The event of the keypress.
     */
    PrimeFaces.widget.SelectOneMenu.prototype.highlightPrev = function(event) {
        // Get the currently active item
        var activeItem = this.getActiveItem();
        
        // Check if the panel is hidden
        var isHidden = this.panel.is(':hidden');
        
        // Create a selector for the next valid option
        // If panel is hidden, select the first option, otherwise select the first visible option
        var selector = '[role="option"]:not(.ui-state-disabled)' + (isHidden ? ':first' : ':visible:first');
        
        // Try to find the previous valid option after the active item
        var prev = activeItem.prevAll(selector);
        
        // If no next option found, look for the first option in the next group (ul)
        if (prev.length === 0) {
            var index = this.items.index(activeItem) - 1;
            if (index >= 0) {
                prev = this.items.eq(index);
            }
        }

        if(prev.length === 1) {
            if(this.panel.is(':hidden')) {
                this.selectItem(prev);
            }
            else {
                this.highlightItem(prev);
                PrimeFaces.scrollInView(this.itemsWrapper, prev);
            }
            this.changeAriaValue(prev);
        }

        event.preventDefault();
    };

};