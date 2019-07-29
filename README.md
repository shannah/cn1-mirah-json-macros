# Codename One Reflection Utilities

This library and associated macro-pack provide pseudo-reflection features for Codename One Applications.  Some applications for these tools include JSON parsing to and from Java objects, wrapping Java Objects with a `Map` interface, copying object properties without boiler-plate code and more.

## Requirements

1. Netbeans 7.4 or higher with Codename One plugin
2. Codename One DataMapper Lib
3. Netbeans Mirah Module 1.0.26 or higher

## Installation

1. Install the `CN1DataMapper` library by downloading the `CN1DataMapper2.cn1lib` file from the [latest release](https://github.com/shannah/cn1-mirah-json-macros/releases/latest) and copying it into your Codename One project's `lib` directory.  (See [the CN1DataMapper project](https://github.com/shannah/cn1-data-utils) for more detailed install instructions).
2. Copy `CN1MirahJSON-macros.jar` from the [latest release](https://github.com/shannah/cn1-mirah-json-macros/releases/latest) into your Codename One project's `lib/mirah/macros` directory.
3. Right click your project in the project explorer, and select "Refresh Libs"
4. Select "Clean & Build" project.

## Using The Tools In this Library

1. [JSON Parsing](docs/json.md)
2. [Reflection(ish) support and POJO Wrappers](docs/beans.md) to be able to interact with them using the `Map` interface.
3. [Javadocs](https://rawgit.com/shannah/cn1-data-utils/master/dist/javadoc/index.html)

##Compilation

Prerequirement : ANT

1. get a copy
2. get the last version of https://github.com/shannah/mirah-macro-utils in dist directory [Jar](https://github.com/shannah/mirah-macro-utils/blob/master/dist/MirahMacroUtils.jar) put in lib
3. get last Mirah ant task from https://github.com/shannah/mirah-ant install it a folder of your choice (my $MYANTLib) for e.g: ~/.ant/libs/mirah/
4. run export ANT_OPTS=-Dlibs.mirah-all.classpath=$MYANTLib/mirah/MirahAnt.jar:$MYANTLib/mirah/mirah.jar:$MYANTLib/mirah/mirahc.jar
5. ant compile

##Run Demo

in CN1MirahJSONDemo

1. set export ANT_OPTS=-Dlibs.mirah-all.classpath=$MYANTLib/mirah/MirahAnt.jar:$MYANTLib/mirah/mirah.jar:$MYANTLib/mirah/mirahc.jar
2. ant run



