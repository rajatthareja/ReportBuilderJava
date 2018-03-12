<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${reportTitle}</title>
    <link rel="icon" href="http://reportbuilder.rajatthareja.com/rb.ico">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css" media="screen,projection"/>
    <link href="https://cdn.rawgit.com/rajatthareja/ReportBuilder/v1.4/css/report.builder.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<#include "header.ftl">

<main class="brown lighten-5">
    <div class="row">

        <#include "overview.ftl">

        <#include "features.ftl">

        <#include "summary.ftl">

        <#include "errors.ftl">

    </div>
</main>

<#include "footer.ftl">

<#list report.getFeatures() as feature>
    <#list feature.getScenarios() as scenario>
        <#assign scenarioId='f' + feature?index + 's' + scenario?index>
        <#include "scenario.ftl">
    </#list>
</#list>

<script>
    $(document).ready(function () {
    <#list report.getFeatures() as feature>
        <#list feature.getScenarios() as scenario>
            $('#f${feature?index}s${scenario?index}').modal();
        </#list>
    </#list>
        $('li:empty').remove();
    });
</script>
<script src="https://cdn.rawgit.com/rajatthareja/ReportBuilder/v1.4/js/report.builder.min.js"></script>
</body>
</html>
