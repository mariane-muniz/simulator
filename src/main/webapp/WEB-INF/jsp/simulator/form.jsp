<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../common/header.jsp" />

<div class="uk-container uk-container-small uk-position-relative">
    <h2 class="uk-h1 tm-heading-fragment" style="text-align: center;">
        ${action} simulation
    </h2>
    <!-- <hr class="uk-divider-icon"> -->
    <form class="uk-form-horizontal uk-margin-large" method="post">
        <c:if test="${not empty simulation.id}">
            <input type="hidden" name="id" value="${simulation.id}"/>
        </c:if>
        <ul uk-tab uk-switcher>
            <li class="uk-active"><a href="#">General</a></li>
            <li><a href="#">Fleet</a></li>
            <li><a href="#">Options</a></li>
        </ul>
        <ul class="uk-switcher"> 
            <li>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">Name</label>
                    <div class="uk-form-controls">
                        <input class="uk-input" id="form-horizontal-text" value="${simulation.name}"
                            type="text" name="name" placeholder="Fill the simulation name" required>
                    </div>
                </div>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-select">Select</label>
                    <div class="uk-form-controls">
                        <select class="uk-select" id="form-horizontal-select" name="status">
                            <option ${'true' eq simulation.status ? 'selected="selected"' : ''} ${value} value='true'>Active</option>
                            <option ${'false' eq simulation.status ? 'selected="selected"' : ''} ${value} value='false'>Inactive</option>
                        </select>
                    </div>
                </div>
            </li>
            <li>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">Fleet size</label>
                    <div class="uk-form-controls">
                        <input class="uk-input" id="form-horizontal-text" value="${simulation.fleetSize}"
                            type="number" min="1" placeholder="Fill the fleet size" name="fleetSize">
                    </div>
                </div>
            </li>
            <li>
                <div class="uk-margin">
                    <label class="uk-form-label" for="form-horizontal-text">Risk Manager ID</label>
                    <div class="uk-form-controls">
                        <input class="uk-input" id="form-horizontal-text" type="number" value="${simulation.riskManagerId}"
                            min="1" placeholder="Fill the risk manager ID" name="riskManagerId">
                    </div>
                </div>
            </li>
        </ul>
        <br>
        <button class="uk-button uk-button-primary uk-width-1-1 uk-margin-small-bottom" type="submit">
            Save
        </button>
    </form>
</div>

<jsp:include page="../common/footer.jsp"/>