<div style="display: block;">
    <h3 class="uk-h1 tm-heading-fragment">
        Simulation Detail: ${simulation.name}
    </h3>
    <hr class="uk-divider-icon">
    <div class="uk-child-width-1-2@s" uk-grid>
        <div>
            <div id="info-panel" class="uk-card uk-card-body uk-padding-remove-right uk-padding-remove-left">
                <span class="info-item"><b>ID:</b> <span id="simulationId">${simulation.id}</span></span>
                <span class="info-item"><b>Status:</b> ${simulation.status ? 'Active' : 'Inactive'}</span>
                <span class="info-item"><b>Fleet size:</b> ${simulation.fleetSize}</span>
                <c:if test="${not empty simulation.riskManagerId}">
                    <span><b>Risk Manager ID:</b> ${simulation.riskManagerId}</span>
                </c:if>
                <br>
                <br>
                Percent of positions sended: <span>20</span>%
                <progress class="uk-progress" value="20" max="100"></progress>
            </div>
        </div>
        <div class="uk-child-width-1-3@s uk-text-center" uk-grid>
            <div>
                <div data-fleet-size="${simulation.fleetSize}"
                    class="uk-background-primary uk-light uk-padding uk-panel">
                    <h1><span id="qty-vehicles">0</span> /${simulation.fleetSize}</h1>
                    <p>Vehicles</p>
                </div>
            </div>
            <div>
                <div class="uk-background-primary uk-light uk-padding uk-panel">
                    <h1><span class="online">0</span>/ <span class="offline">0</span></h1>
                    <p>Online/offline</p>
                </div>
            </div>
            <div>
                <div class="uk-background-primary uk-light uk-padding uk-panel">
                    <h1 id="qty-positions">0</h1>
                    <p>positions</p>
                </div>
            </div>
            <div>
                <div class="uk-background-primary uk-light uk-padding uk-panel">
                    <h1>0</h1>
                    <p>Login events</p>
                </div>
            </div>
            <div>
                <div class="uk-background-primary uk-light uk-padding uk-panel">
                    <h1>0</h1>
                    <p>Logoff events</p>
                </div>
            </div>
            <div>
                <div class="uk-background-primary uk-light uk-padding uk-panel">
                    <h1>0</h1>
                    <p>Logoff events</p>
                </div>
            </div>
        </div>
    </div>
    <hr class="uk-divider-icon">
    <div>
        <div class="uk-background-muted uk-padding uk-panel" id="positions"></div>
    </div>
</div>
<style>
    #info-panel span.info-item {
        display: block;
    }
</style>