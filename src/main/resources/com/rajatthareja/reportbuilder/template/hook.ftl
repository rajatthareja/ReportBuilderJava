<li class="collection-item hook"><#if hook.getResult().getErrorMessage()??>
    <pre class="error">${hook.getResult().getErrorMessage()}</pre>
</#if>
<#if hook.getEmbeddings()??>
    <#list hook.getEmbeddings() as embedding>
        <#include "embedding.ftl">
    </#list>
</#if></li>