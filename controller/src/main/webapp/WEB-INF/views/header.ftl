<#import "/spring.ftl" as spring/>
<header>
    <div class="uui-header">
        <nav>
            <!--Responsive html-layuot-->
            <div class="uui-responsive-header">
                <div class="responsive-header">
                    <a href="#" class="responsive-brand-logo">
                        <span class="arrow fa fa-angle-left"></span>
                        <span class="logo">
                            <img src="uui/images/ic_logo_UUi.svg" alt="" />
                        </span>
                        <span class="title">Unified UI</span>
                    </a>
                </div>
                <div class="responsive-menu">
                    <div class="menu-wrapper">
                        <div class="menu-scroll">
                            <ul class="nav navbar-nav">
                                <li class="sub-menu profile-menu">
                                    <a href="#"><span>Profile</span></a>
                                    <ul class="sub">
                                        <li class="login"><a href="#"><span>Log in</span></a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <!---->
            <a href="#" class="brand-logo">
                <span class="logo">
                    <img src="uui/images/ic_logo_UUi.svg" alt="" />
                </span>
                Unified UI
            </a>
            <ul class="uui-header-tools nav navbar-nav">
                <li class="dropdown uui-profile-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <div class="profile-photo">
                            <img src="uui/images/icons/no_photo.png" alt="" />
                        </div>
                    </a>
                    <div class="dropdown-menu" role="menu">
                        <span class="menu-arrow"></span>
                        <ul class="profile-links">
                            <li class="login"><a href="#"><i class="fa fa-sign-in"></i>Log in</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
        </nav>
        <a href="tours?lang=en">English </a> | <a href="tours?lang=ru">Russian </a>
    </div>
</header>