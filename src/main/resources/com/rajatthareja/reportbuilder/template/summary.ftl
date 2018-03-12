<div id="summary" class="col s12 brown lighten-5">
    <table id="summaryTable" class="bordered brown lighten-1 white-text">
        <thead>
        <tr>
            <th>Feature</th>
            <th>Scenario</th>
            <th>Status</th>
            <th class="hide-on-small-only">Error</th>
        </tr>
        </thead>
        <tbody>
        <#list report.getFeatures() as feature>
            <#list feature.getScenarios() as scenario>
                <#if scenario.getKeyword()!='Background'>
                    <tr class="${scenario.getStatus()}">
                        <td class="hoverable">${feature.getName()}</td>
                        <td class="hoverable"><a class="modal-trigger white-text" href="#f${feature?index}s${scenario?index}">${scenario.getName()}</a></td>
                        <td class="hoverable uppercase">${scenario.getStatus()}</td>
                        <td class="hoverable hide-on-small-only"><#if scenario.getError()??>${scenario.getError()}</#if></td>
                    </tr>
                </#if>
            </#list>
        </#list>
        </tbody>
    </table>
</div>