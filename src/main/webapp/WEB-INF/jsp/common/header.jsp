<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Simulator</title>
    <link rel="stylesheet" href="/css/uikit.min.css" />
    <link rel="stylesheet" href="https://getuikit.com/css/theme.css?1312" />
</head>
<body>
    <div uk-sticky class="uk-navbar-container tm-navbar-container uk-sticky uk-sticky-fixed">
        <div class="uk-container uk-container-expand">
            <nav class="uk-navbar">
                <div class="uk-navbar-left">
                    <a href="/" class="uk-padding-small" style="display:inline-block">
                        <img uk-svg="" src="/img/uikit-logo.svg" class="uk-margin-small-right" hidden="true">
                    </a>
                    <div style="display:inline-block">
                        <ul class="uk-breadcrumb" style="margin-bottom: auto;">
                            <c:forEach items="${breadCrumb.links}" var="link">
                                <li><a href="${link.url}">${link.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="uk-navbar-right">
                    <ul class="uk-navbar-nav">
                        <li>
                            <a href="/simulator/list">
                                <span uk-icon="rss" style="margin-right: 10px;"></span>
                                Simulations
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </div>
    <div class="tm-main uk-section uk-section-default">
