<div id="${scenarioId}" class="modal modal-fixed-footer">
    <div class="modal-content">
        <#if scenario.getTags()??>
            <#list scenario.getTags() as tag>
                <div class="chip"><i class="material-icons rotate-45">label</i>${tag.getName()}</div>
            </#list>
        </#if>

        <h5>${scenario.getName()}</h5>
        ${feature.getUri()}:${scenario.getLine()}

        <p><ul class="collection stepList">
            <#if scenario.getBefore()??>
                <#list scenario.getBefore() as hook>
                    <#include "hook.ftl">
                </#list>
            </#if>

             <#list scenario.getSteps() as step>
                <#if step.getBefore()??>
                    <#list step.getBefore() as hook>
                        <#include "hook.ftl">
                    </#list>
                </#if>

                <#include "step.ftl">

                <#if step.getAfter()??>
                    <#list step.getAfter() as hook>
                        <#include "hook.ftl">
                    </#list>
                </#if>
            </#list>

            <#if scenario.getAfter()??>
                 <#list scenario.getAfter() as hook>
                    <#include "hook.ftl">
                </#list>
            </#if>
        </ul></p>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat"><i class="material-icons">close</i></a>
    </div>
</div>