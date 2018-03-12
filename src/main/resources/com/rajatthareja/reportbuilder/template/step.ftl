<li class="collection-item step ${step.getResult().getStatus()}">
    <b>${step.getKeyword()}</b> ${step.getName()}
    <#if step.getRows()??>
        <table>
            <tbody>
                <#list step.getRows() as row>
                <tr><td>${row}</td></tr>
                </#list>
            </tbody>
        </table>
    </#if>
    <#if step.getResult().getErrorMessage()??>
        <pre class="error">${step.getResult().getErrorMessage()}</pre>
    </#if>
    <#if step.getEmbeddings()??>
        <#list step.getEmbeddings() as embedding>
            <#include "embedding.ftl">
        </#list>
    </#if>
</li>