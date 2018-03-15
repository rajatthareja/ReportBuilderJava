<header class="${reportColor} lighten-5">
    <div class="row ${reportColor} lighten-1">
        <div class="col m8 hide-on-small-only">
            <h5 class="truncate white-text tooltipped" data-tooltip="${reportTitle}">${reportTitle}</h5>
        </div>
        <div class="col m4 s12 ${reportColor} lighten-1">
            <ul class="tabs ${reportColor} lighten-1">
                <li class="tab col s3"><a
                        class="btn ${reportColor} lighten-3 active blue-text waves-effect waves-light tooltipped"
                        data-tooltip="Overview" href="#overview"><i class="material-icons">public</i></a></li>
                <li class="tab col s3"><a class="btn ${reportColor} lighten-3 white-text waves-effect waves-light tooltipped"
                                          data-tooltip="Features" href="#features"><i class="material-icons">view_headline</i></a>
                </li>
                <li class="tab col s3"><a class="btn ${reportColor} lighten-3 white-text waves-effect waves-light tooltipped"
                                          data-tooltip="Summary" href="#summary"><i
                        class="material-icons">view_comfy</i></a></li>
                <#if report.getErrors()?has_content><li class="tab col s3"><a class="btn ${reportColor} lighten-3 white-text waves-effect waves-light tooltipped"
                                          data-tooltip="Errors" href="#errors"><i class="material-icons">bug_report</i></a>
                </li></#if>
            </ul>
        </div>
    </div>
</header>