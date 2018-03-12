<div id="errors" class="col s12 brown lighten-5">
    <ul class="errorList collapsible popout" data-collapsible="expandable">
    <#list report.getErrors() as error>
        <li class="error">
            <div class="collapsible-header red lighten-2 white-text waves-effect waves-light">
                <i class="material-icons">bug_report</i>${error}
            </div>
            <div class="collapsible-body brown lighten-4">
                <ul class="collection failedScenarioList">
                    <#list report.getFeatures() as feature>
                        <#list feature.getScenarios() as scenario>
                            <#if scenario.getError()??>
                                <#if scenario.getError()==error>
                                    <li class="collection-item failedScenario brown lighten-5">
                                        <i class="material-icons red-text">highlight_off</i>&nbsp;<a
                                            class="modal-trigger red-text"
                                            href="#f${feature?index}s${scenario?index}">${scenario.getName()}</a>
                                    </li>
                                </#if>
                            </#if>
                        </#list>
                    </#list>
                </ul>
            </div>
        </li>
    </#list>
    </ul>
</div>