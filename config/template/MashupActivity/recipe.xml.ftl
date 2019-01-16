<?xml version="1.0"?>
<recipe>
    <merge from="root/AndroidManifest.xml.ftl"
                   to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <instantiate from="root/src/app_package/Component.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/di/${componentClass}.java" />

    <instantiate from="root/src/app_package/Activity.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${activityClass}.java" />

    <instantiate from="root/src/app_package/ViewModel.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${viewModelClass}.java" />
    <open file="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/${viewModelClass}.java" />

    <instantiate from="root/src/app_package/Fragment.java.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/ui/${fragmentClass}.java" />
    <open file="${escapeXmlAttribute(srcOut)}/${classToResource(featureName)}/ui/${fragmentClass}.java" />

    <instantiate from="root/res/layout/fragment.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(fragmentLayout)}.xml" />
    <open file="${escapeXmlAttribute(resOut)}/layout/${fragmentLayout}.xml" />

</recipe>
