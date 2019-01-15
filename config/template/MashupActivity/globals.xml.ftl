<?xml version="1.0"?>
<globals>

    <global id="baseViewModelClass" value="BaseViewModel" />
    <global id="baseActivityClass" value="BaseActivity" />
    <global id="baseFragmentClass" value="BaseFragment" />
    <global id="activityClass" value="${featureName}Activity" />
    <global id="featurePackageName" value="${packageName}.${classToResource(featureName)}" />

    <global id="hasNoActionBar" type="boolean" value="false" />
    <global id="parentActivityClass" value="" />
    <global id="excludeMenu" type="boolean" value="true" />
    <global id="generateActivityTitle" type="boolean" value="false" />
    <#include "../common/common_globals.xml.ftl" />
</globals>
