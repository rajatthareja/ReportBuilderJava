<div id="features" class="col s12 brown lighten-5">
    <ul class="featureList collapsible popout" data-collapsible="expandable">
    <#list report.getFeatures() as feature>
        <li class="feature ${feature.getStatus()}">
            <div class="collapsible-header brown lighten-1 white-text waves-effect waves-light">
                <i class="material-icons white-text">featured_play_list</i>
                <b>${feature.getKeyword()}</b>&nbsp;${feature.getName()}
            </div>
            <div class="collapsible-body brown lighten-4">
                <#if feature.getTags()??>
                    <#list feature.getTags() as tag>
                        <div class="chip"><i class="material-icons rotate-45">label</i>${tag.getName()}</div>
                    </#list>
                </#if>
                <ul class="collection scenarioList">
                    <#list feature.getScenarios() as scenario>
                        <#if scenario.getKeyword()=='Background'>
                            <li class="collection-item background ${scenario.getStatus()}">
                                <a class="waves-effect waves-light modal-trigger white-text" href="#f${feature?index}s${scenario?index}">
                                    <b>${scenario.getKeyword()}</b> ${scenario.getName()}
                                </a>
                            </li>
                        <#else>
                        <li class="collection-item scenario ${scenario.getStatus()}">
                            <a class="waves-effect waves-light modal-trigger white-text" href="#f${feature?index}s${scenario?index}">
                                <b>${scenario.getKeyword()}</b> ${scenario.getName()}
                            </a>
                        </li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </li>
    </#list>
    </ul>
</div>