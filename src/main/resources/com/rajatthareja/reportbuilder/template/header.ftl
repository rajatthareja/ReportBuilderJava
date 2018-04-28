<header class="${reportColor} lighten-5">
    <div class="row ${reportColor} lighten-1">
        <div class="col m6 hide-on-small-only">
            <h5 class="truncate white-text tooltipped" data-tooltip="${reportTitle}">${reportTitle}</h5>
        </div>
        <div class="col m6 s12 ${reportColor} lighten-1">
            <ul class="tabs ${reportColor} lighten-1 row" style="overflow-x: hidden">
                <li class="tab col s3"><a class=" ${reportColor} lighten-3 active blue-text waves-effect waves-light tooltipped" data-tooltip="Results Overview" href="#overview"><i class="material-icons">assessment</i> Overview</a></li>
                <li class="tab col s3"><a class=" ${reportColor} lighten-3 white-text waves-effect waves-light tooltipped" data-tooltip="Scenarios by Features" href="#features"><i class="material-icons">view_headline</i> Features</a></li>
                <li class="tab col s3"><a class=" ${reportColor} lighten-3 white-text waves-effect waves-light tooltipped" data-tooltip="Scenarios Summary Table" href="#summary"><i class="material-icons">view_comfy</i> Summary</a></li>
                <#if report.getErrors()?has_content>
                    <li class="tab col s3"><a class=" ${reportColor} lighten-3 white-text waves-effect waves-light tooltipped" data-tooltip="Failed Scenarios" href="#errors"><i class="material-icons">bug_report</i> Errors</a></li>
                </#if>
            </ul>
        </div>
    </div>
</header>