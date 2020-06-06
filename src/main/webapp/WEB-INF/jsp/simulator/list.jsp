<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../common/header.jsp" />

<div class="uk-container uk-container-medium uk-position-relative">
    <h2 class="uk-h1 tm-heading-fragment" style="float: left;">
        Simulations
    </h2>
    <a class="uk-button uk-button-primary" style="float: right;" href="/simulator/list/add">
        Create
    </a>
    <table class="uk-table uk-table-hover uk-table-divider uk-table-justify uk-table-small">
        <thead>
            <tr>
                <th class="uk-width-small">ID</th>
                <th class="uk-width-small">Status</th>
                <th>Title</th>
                <th class="uk-width-small">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="simulation" items="${simulations}">
                <tr>
                    <td class="uk-width-small">${simulation.id}</td>
                    <td class="uk-width-small">
                        <c:if test="${simulation.status eq true}"><span class="uk-badge">Active</span></c:if>
                        <c:if test="${simulation.status eq false}">Inactive</c:if>
                    </td>
                    <td>${simulation.name}</td>
                    <td class="uk-width-small">
                        <div class="uk-margin-small">
                            <div class="uk-button-group">
                                <a class="uk-button"
                                    href="/simulator/detail/${simulation.id}">Detail</button>
                                <a class="uk-button"
                                    href="/simulator/list/edit/${simulation.id}">Edit</button>
                                <a class="uk-button" 
                                    href="/simulator/list/remove/${simulation.id}">Remove</a>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <ul class="uk-pagination uk-flex-center" style="display: none;" uk-margin>
        <li><a href=""><span uk-pagination-previous></span></a></li>
        <li><a href="#">1</a></li>
        <li class="uk-disabled"><span>...</span></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">6</a></li>
        <li class="uk-active"><span>7</span></li>
        <li><a href="#">8</a></li>
        <li><a href="#">9</a></li>
        <li><a href="#">10</a></li>
        <li class="uk-disabled"><span>...</span></li>
        <li><a href="#">20</a></li>
        <li><a href=""><span uk-pagination-next></span></a></li>
    </ul>
</div>
<jsp:include page="../common/footer.jsp"/>