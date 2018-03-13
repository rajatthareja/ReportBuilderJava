<div id="overview" class="col s12 ${reportColor} lighten-5">
    <div class="row">
        <div class="col m4 s12">
            <canvas id="featuresDoughnut" width="400" height="400"></canvas>
            <table id="metaDataFeatures" class="bordered">
                <tbody></tbody>
            </table>
        </div>
        <div class="col m4 s12">
            <canvas id="scenariosDoughnut" width="400" height="400"></canvas>
            <table id="metaDataScenarios" class="bordered">
                <tbody></tbody>
            </table>
        </div>
        <div class="col m4 s12">
            <table id="metaData" class="bordered">
                <tbody>
                <#list additionalInfo?keys as key>
                <tr><th>${key}</th><td>${additionalInfo[key]}</td></tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>