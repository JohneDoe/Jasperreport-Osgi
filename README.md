# Jasperreport-Osgi

I want create a pdf by using jasperreports.
This file should inculude a svg.

Jasperreports 6.4.2 needs
xmlgraphics batik 1.9
needs...
<dependency>
    <groupId>org.apache.servicemix.bundles</groupId>
    <artifactId>org.apache.servicemix.bundles.batik</artifactId>
    <version>1.9_1</version>
</dependency>


I tryed many ways, here are 2:

1: including every single jar of org.w3c. (this is the jaspersoftstudio eclipse plugin way)

*svgWithW3cAndJavaxXml.bndrun*

When starting all cores of my cpu went up but nothing happened 



2: bundle xml-apis(-ext)

svgWithXmlApis.bndrun

here i have some problem with:
requirement &(osgi.ee=JavaSE)(version=1.1)]


you can see/start it in project *my.sandbox.report.svgtest* 
