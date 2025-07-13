# xml-doclet changelog

Changelog of xml-doclet.

## 2.1.0 (2025-07-13)

### Features

-  support MD, ADOC, Docbook ([44ec5](https://github.com/manticore-projects/xml-doclet/commit/44ec50f40d4706d) manticore-projects)  
-  working markdown stylesheet ([f4571](https://github.com/manticore-projects/xml-doclet/commit/f45710453e09854) manticore-projects)  
-  working docbook stylesheet ([d61c5](https://github.com/manticore-projects/xml-doclet/commit/d61c5020563ebfe) manticore-projects)  

### Other changes


## 2.0.9 (2025-07-12)

### Features

-  implement `Xdoclint` and `html5` options since those are set by Gradle and can't get switched off ([32ae4](https://github.com/manticore-projects/xml-doclet/commit/32ae49181922fc1) manticore-projects)  

## 2.0.8 (2025-07-12)

### Other changes


## 2.0.7 (2025-07-12)

### Other changes


## 2.0.6 (2025-07-12)

### Other changes


## 2.0.5 (2025-07-12)

### Other changes


## 2.0.4 (2025-07-12)

### Other changes


## 2.0.3 (2025-03-03)

### Bug Fixes

-  **parser**  remove extraneous commas around HTML entities and tags in JavaDoc comments ([3f126](https://github.com/manticore-projects/xml-doclet/commit/3f12619f9a63fab) Roberto Abdelkader Martínez Pérez)  

### Other changes

**Merge pull request #13 from nilp0inter/fix/issue-12**

* fix(parser): remove extraneous commas around HTML entities and tags 

[45544](https://github.com/manticore-projects/xml-doclet/commit/45544bdbbec34d4) manticore-projects *2025-03-03 23:32:48*

**Update README.md**

* Adds quotes around -d parameter so that it works for paths with and without spaces. 

[6b81b](https://github.com/manticore-projects/xml-doclet/commit/6b81b6e3d69d449) Manoel Campos *2025-03-02 13:16:43*


## 2.0.2 (2025-02-28)

### Bug Fixes

-  ClassCastException in Javadoc Generation with XmlDoclet ([f1dc7](https://github.com/manticore-projects/xml-doclet/commit/f1dc7f4e5b8a216) Manoel Campos)  

### Other changes

**Fix "Space-assignment syntax in Groovy DSL has been deprecated.`"**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[b5db3](https://github.com/manticore-projects/xml-doclet/commit/b5db386d299f5fe) Manoel Campos *2025-02-27 21:06:11*

**Address deprecation warning "Invocation of Task.project at execution time"**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[00e72](https://github.com/manticore-projects/xml-doclet/commit/00e7241b18ec4bf) Manoel Campos *2025-02-27 21:06:06*

**Fix project.buildDir deprecation on getVersion inside build.gradle**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[beb28](https://github.com/manticore-projects/xml-doclet/commit/beb287b175d5d49) Manoel Campos *2025-02-27 20:59:52*

**Fix project.exec deprecation on getVersion inside build.gradle**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[09ae4](https://github.com/manticore-projects/xml-doclet/commit/09ae4e0fc62de7f) Manoel Campos *2025-02-27 20:59:52*

**Splits build and publish jobs into different workflows.**

* This way, publish is not called for PRs. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[c7171](https://github.com/manticore-projects/xml-doclet/commit/c7171d033e76b86) Manoel Campos *2025-02-27 20:59:42*

**Adds try block to catch exceptions when getting the package from a given element.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[5b78b](https://github.com/manticore-projects/xml-doclet/commit/5b78b58cdfe2fdf) Manoel Campos *2025-02-27 20:58:05*

**Merge remote-tracking branch 'origin/master'**


[a85e0](https://github.com/manticore-projects/xml-doclet/commit/a85e08498935f32) Andreas Reichel *2025-02-16 00:32:15*


## 2.0.1 (2025-02-16)

### Features

-  restore JDK11 compatibility ([ac50e](https://github.com/manticore-projects/xml-doclet/commit/ac50e07cee5fed2) Andreas Reichel)  

### Bug Fixes

-  build and release for JDK 17+ ([1b18f](https://github.com/manticore-projects/xml-doclet/commit/1b18f0e6b05acf0) Andreas Reichel)  
-  enforce the correct classloader to be used ([84b26](https://github.com/manticore-projects/xml-doclet/commit/84b260e89c73674) manticore-projects)  
-  check if instance before casting ([9f6e0](https://github.com/manticore-projects/xml-doclet/commit/9f6e08ca60f30fb) manticore-projects)  
-  use a concurrent map for allowing concurrent modifications ([2b734](https://github.com/manticore-projects/xml-doclet/commit/2b7343df14b54a4) Andreas Reichel)  

### Other changes

**Merge remote-tracking branch 'origin/master' into jdk11**

* # Conflicts: 
* #	src/main/java/com/manticore/tools/xmldoclet/Parser.java 
* #	src/test/java/com/manticore/tools/xmldoclet/ClassTest.java 
* #	src/test/java/com/manticore/tools/xmldoclet/FieldTest.java 
* #	src/test/java/com/manticore/tools/xmldoclet/JavaDocElements.java 

[98701](https://github.com/manticore-projects/xml-doclet/commit/98701b380e11205) Andreas Reichel *2025-02-04 10:56:42*

**Fix README format**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[6d032](https://github.com/manticore-projects/xml-doclet/commit/6d032f8c6463c94) Manoel Campos *2025-02-04 10:21:54*

**Changes package name from `com.manticoreprojects.tools.xmldoclet` to `com.manticore.tools.xmldoclet.xjc` everywhere**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[fcbfa](https://github.com/manticore-projects/xml-doclet/commit/fcbfadc614669ee) Manoel Campos *2025-02-04 10:20:50*

**try JDK 11 and 17 too**


[dea65](https://github.com/manticore-projects/xml-doclet/commit/dea654dc09495d6) manticore-projects *2025-02-04 03:01:41*

**Update XmlDoclet version on README.md**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[b6e2e](https://github.com/manticore-projects/xml-doclet/commit/b6e2ef224802f42) Manoel Campos *2025-02-03 20:16:36*

**Renames package com.github.markusbernhardt.xmldoclet to com.manticoreprojects.tools.xmldoclet**

* Updates the following files 
* - Docs 
* - Configuration files 
* - Tests 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[706e7](https://github.com/manticore-projects/xml-doclet/commit/706e704fd6f3951) Manoel Campos *2025-02-03 20:05:46*

**Update author names**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[fc96a](https://github.com/manticore-projects/xml-doclet/commit/fc96aa8974696f7) Manoel Campos *2025-02-03 19:55:17*

**Fix #6**

* The new Doclet API has a different way to process each option. 
* Each Option instance is usually created as an anonymous class to implement 
* the process method. This method is called when a given command line option 
* matches the option name. The method is therefore accountable 
* to validate the option arguments. 
* Since creating an anonymous class is not usual nowadays, 
* the CustomOption class previously was changed to accept a 
* &#x60;BiPredicate&lt;String, List&lt;String&gt;&gt; argumentsProcessor&#x60; 
* function that enables each option instance to define 
* how the process method will be implemented, 
* without the need to create an anonymous class. 
* However, this argumentsProcessor was not being used yet. 
* Now, when an Option is created, the constructor receives 
* a BiPredicate that defines the implementation of the processor method. 
* There is no need for the Apache Commons CLI anymore, 
* (mainly because the doclet options are very simple) 
* and a lot of code could be removed, simplifying the implementation. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[0b959](https://github.com/manticore-projects/xml-doclet/commit/0b959a0551bb90f) Manoel Campos *2025-02-03 19:43:06*

**Remove `--warning-mode none` from CI build**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[fb0d7](https://github.com/manticore-projects/xml-doclet/commit/fb0d7a76099e548) Manoel Campos *2025-02-03 17:28:09*

**CI build with JDK 21 to 23**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[4194f](https://github.com/manticore-projects/xml-doclet/commit/4194f859e05bfd7) Manoel Campos *2025-01-31 22:42:27*

**Fix build failure on CI**

* - Adds annotation &#x60;@Deprecated&#x60; to Tag1 file that was deprecated via javadoc tag. 
* - Adds org.junit.platform:junit-platform-launcher that was causing tests to fail on CI. 
* - Build and run tests in a single pass again (after spotting the CI build issues). 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[a7297](https://github.com/manticore-projects/xml-doclet/commit/a729754b7be7ad5) Manoel Campos *2025-01-31 22:26:22*

**Split test execution from build**

* because tests were failing only on the CI 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[e349c](https://github.com/manticore-projects/xml-doclet/commit/e349cd6e9fb2396) Manoel Campos *2025-01-31 22:09:20*

**Change gradle build command to try to avoid build failure on CI**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[32fa0](https://github.com/manticore-projects/xml-doclet/commit/32fa0e00f1c18d7) Manoel Campos *2025-01-31 22:03:39*

**Adds "main" branch as a valid branch name for CI build**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[7b356](https://github.com/manticore-projects/xml-doclet/commit/7b356f795ee3106) Manoel Campos *2025-01-31 22:03:34*

**Makes AnnotationParser package-private to allow**

* its utilization only inside the package 
* and avoid the spotbugs EI_EXPOSE_REP2 issue about the parser parameter. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[db83e](https://github.com/manticore-projects/xml-doclet/commit/db83e8623ff4e52) Manoel Campos *2025-01-31 21:35:23*

**Refactor usage of AnnotationParser for reduction or memory consumption**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[02c19](https://github.com/manticore-projects/xml-doclet/commit/02c19c0464e8a78) Manoel Campos *2025-01-31 21:06:01*

**Rename constant in TypeUtils**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[0b413](https://github.com/manticore-projects/xml-doclet/commit/0b41398561191e1) Manoel Campos *2025-01-31 20:46:04*

**Disables spotless on AnnotationParser because it was crazily joining lines without any sense.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[66dc0](https://github.com/manticore-projects/xml-doclet/commit/66dc0db5e0815c5) Manoel Campos *2025-01-31 20:44:49*

**Spotless code format**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[0139c](https://github.com/manticore-projects/xml-doclet/commit/0139cb135038af9) Manoel Campos *2025-01-31 20:42:37*

**Extract methods that parses annotation inside the Parser to a new AnnotationParser class.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[ddb7b](https://github.com/manticore-projects/xml-doclet/commit/ddb7b375d4f7542) Manoel Campos *2025-01-31 20:22:17*

**Refactor Parser extracting new methods for clarity**

* The Parser.parseAnnotationDesc method was too long. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[79df7](https://github.com/manticore-projects/xml-doclet/commit/79df7004fcf1b31) Manoel Campos *2025-01-31 19:15:01*

**Disable spotbugs ST_WRITE_TO_STATIC_FROM_INSTANCE_METHOD check**

* due lack of way to get the XmlDoclet instance created by the javadoc CLI, 
* without storing the instance as a static field. 
* Check https://bugs.openjdk.org/browse/JDK-8263219 for details. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[22eb5](https://github.com/manticore-projects/xml-doclet/commit/22eb51d6a3d85da) Manoel Campos *2025-01-31 19:03:18*

**Extract code that runs the javadoc CLI from AbstractTest to JavaDocCLI new class**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[bd18a](https://github.com/manticore-projects/xml-doclet/commit/bd18af6b9f2cc88) Manoel Campos *2025-01-31 18:33:00*

**AbstractTest cleanup**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[ca614](https://github.com/manticore-projects/xml-doclet/commit/ca6149a7a854c6f) Manoel Campos *2025-01-31 17:13:24*

**Disable SpotBugs EI_EXPOSE_REP ("may expose internal representation") check due to false positives.**

* See https://github.com/spotbugs/spotbugs/issues/2083 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[dd92e](https://github.com/manticore-projects/xml-doclet/commit/dd92e8ae3e0fdf2) Manoel Campos *2025-01-31 17:12:56*

**Explicitly set the spotbugs version**

* (without that, on older version was being used). 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[5988d](https://github.com/manticore-projects/xml-doclet/commit/5988d97ee8bde42) Manoel Campos *2025-01-31 16:47:38*

**Checkstyle code format**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[97758](https://github.com/manticore-projects/xml-doclet/commit/97758d28f60687a) Manoel Campos *2025-01-31 16:46:45*

**Disable requirement of braces for single line blocks in checkstyle.xml**

* to less code verbosity. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[fdc67](https://github.com/manticore-projects/xml-doclet/commit/fdc67ed62c12c64) Manoel Campos *2025-01-31 16:33:39*

**Fix javadoc**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[56bfa](https://github.com/manticore-projects/xml-doclet/commit/56bfae35b4c02d7) Manoel Campos *2025-01-31 16:29:09*

**Fix Parser.parseAnnotationDesc and ClassTest.testClassAnnotationCascade**

* for ClassAnnotationCascade tests. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[febb0](https://github.com/manticore-projects/xml-doclet/commit/febb0fa20c85cf1) Manoel Campos *2025-01-31 16:21:42*

**Refactor Parser.getAnnotationArgumentType()**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[29034](https://github.com/manticore-projects/xml-doclet/commit/29034aa99a89afe) Manoel Campos *2025-01-30 23:01:59*

**Refactor Parser.getAnnotationArgumentType()**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[05848](https://github.com/manticore-projects/xml-doclet/commit/05848e7f9127267) Manoel Campos *2025-01-30 23:00:27*

**Refactor Parser.getAnnotationArgumentType()**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[e6725](https://github.com/manticore-projects/xml-doclet/commit/e6725e84f79d2d5) Manoel Campos *2025-01-30 22:59:24*

**Format ClassAnnotationCascade**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[ae9ca](https://github.com/manticore-projects/xml-doclet/commit/ae9ca0e064a02f9) Manoel Campos *2025-01-30 22:35:29*

**Fix Parser.parseConstructor to correctly get the constructor name**

* as the class name (instead of &quot;&lt;init&gt;&quot; as it&#x27;s obtained if called constructorDoc.getSimpleName()) 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[ebf57](https://github.com/manticore-projects/xml-doclet/commit/ebf574001ced26e) Manoel Campos *2025-01-30 22:32:01*

**Fix Parser.parseAnnotationDesc to correctly get the value**

* when an annotation argument is not an array 
* (needing no iteration to get such a value 
* and add the annotationArgumentNode.getValue() list). 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[c1e24](https://github.com/manticore-projects/xml-doclet/commit/c1e24e046b21fff) Manoel Campos *2025-01-30 22:30:29*

**Adds a Parser.getAnnotationArgumentType() method**

* that correctly parses the data type of an annotation argument. 
* When extracting the type information for a given annotation argument, 
* it was being returned the signature of the method in the interface that defines the annotation. 
* This method is used to internally set a value for the argument. 
* But we are interested only in knowing the data type of the argument. 
* This way, we need to get the return type of this method for that. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[ff883](https://github.com/manticore-projects/xml-doclet/commit/ff883b9e35a16c1) Manoel Campos *2025-01-30 22:17:20*

**Fix the multiple implementations of the getQualifiedName() and getMethodSignature()**

* that were retuning an odd string representation for method signatures. 
* All implementations call a base getQualifiedName() implementation. 
* The TypeMirror.toString() method returns the fully qualified name of the type. 
* If the type is a method signature, it places the parameters list (parenteses) 
* before the return type (that is void if none), which is an odd convention for Java Code. 
* This way, we invert that order for a conventional representation of a method signature. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[c3f4a](https://github.com/manticore-projects/xml-doclet/commit/c3f4a3c2bc401ec) Manoel Campos *2025-01-30 21:11:36*

**Refactor ClassTest**

* Fix assertion parameters order 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[e535f](https://github.com/manticore-projects/xml-doclet/commit/e535fac2e155881) Manoel Campos *2025-01-30 20:41:53*

**Fix Parser.parseTypeParameter that was causing InterfaceTest to fail**

* For generic type parameters with multiple types 
* (such as Interface8&lt;Fun extends Number &amp; Runnable&gt;), 
* where Number and Runnable are type parameters, 
* the method was returning the bound as a single string representation. 
* However, the tests were expecting them to be defined as two different bounds. 
* Since a class/interface can just have either a lower or upper bound type parameter at the same time, 
* the return of the TypeParameter.getBounds() is either the list of lower bound types or upper bound types. 
* For a type such as Interface8&lt;Fun extends Number &amp; Runnable&gt;, we have two upper bound types. 
* For a type such as Interface8&lt;Fun super   Number &amp; Runnable&gt;, we have two lower bound types. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[d5195](https://github.com/manticore-projects/xml-doclet/commit/d5195a7cbc5df6c) Manoel Campos *2025-01-30 20:26:52*

**Refactor InterfaceTest**

* - Fix assertion parameters order 
* - Simplify assertions: replaces assertEquals(0, list.size()) by assertTrue(list.isEmpty()) 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[cfbed](https://github.com/manticore-projects/xml-doclet/commit/cfbed8483bba9e3) Manoel Campos *2025-01-30 19:50:57*

**Refactor ClassTest**

* - Fix assertion parameters order 
* - Simplify assertions: replaces assertEquals(0, list.size()) by assertTrue(list.isEmpty()) 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[e1804](https://github.com/manticore-projects/xml-doclet/commit/e1804433fc42eff) Manoel Campos *2025-01-30 19:31:41*

**Fix expected method signature,**

* since the new API now provides a different string representation for that 
* (providing an uncommon way to represent method return type after the method parameters). 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[0a774](https://github.com/manticore-projects/xml-doclet/commit/0a7747dd70750fd) Manoel Campos *2025-01-30 15:39:03*

**Fix expected types for FieldTest and MethodsBTest**

* since the new API now provides more information 
* about parameters, qualified names 
* and method returns. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[06c0d](https://github.com/manticore-projects/xml-doclet/commit/06c0d2c662e6c22) Manoel Campos *2025-01-30 15:25:23*

**Fix parsing of generic parameters**

* providing null checks. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[3238f](https://github.com/manticore-projects/xml-doclet/commit/3238f9d01333f56) Manoel Campos *2025-01-30 15:24:15*

**Fix annotation value parsing.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[95b8e](https://github.com/manticore-projects/xml-doclet/commit/95b8e3acba1a6e3) Manoel Campos *2025-01-30 14:31:26*

**Code format**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[c54f1](https://github.com/manticore-projects/xml-doclet/commit/c54f1f51a5f17cd) Manoel Campos *2025-01-30 14:30:48*

**Fix JUnit 5 config for Gradle**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[7af5d](https://github.com/manticore-projects/xml-doclet/commit/7af5d1987948c00) Manoel Campos *2025-01-29 22:13:07*

**Refactor/fix some tests and upgrade to JUnit 5**

* - Replaces checking size&#x3D;&#x3D;0 by isEmpty 
* - Thrown AssertionError inside of FieldTest.findByFieldName to avoid 
* null return (despite the old fail call avoided this null 
* return, the IDE was not aware of). 
* Additionally, shows a clear error message. 
* - Fix assertion parameters order 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[9b8ec](https://github.com/manticore-projects/xml-doclet/commit/9b8ec0cfa04546a) Manoel Campos *2025-01-29 20:04:46*

**Fix null issue that was causing test failures**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[f6a33](https://github.com/manticore-projects/xml-doclet/commit/f6a3379b76ade00) Manoel Campos *2025-01-29 19:41:11*

**Fix to way to get the qualified name**

* that was causing test failures. 
* Just the simple name was being got. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[d227e](https://github.com/manticore-projects/xml-doclet/commit/d227e90d8d01f88) Manoel Campos *2025-01-29 19:40:50*

**Refactors CustomOption**

* Remove redundant Collections.unmodifiableList(names), 
* since the list is unmodifiable already. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[81339](https://github.com/manticore-projects/xml-doclet/commit/81339911c4e6290) Manoel Campos *2025-01-28 20:09:35*

**Refactors CustomOption**

* Defines a BiPredicate&lt;String, List&lt;String&gt;&gt; argumentsProcessor function parameter 
* to enable providing a arguments validation function for each created option 
* without requiring to create a subclass for each option to provide a custom validation function. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[91b5d](https://github.com/manticore-projects/xml-doclet/commit/91b5db9e7e43284) Manoel Campos *2025-01-28 20:08:23*

**Refactors CustomOption**

* Changes the order of attributes and constructor parameters 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[0826e](https://github.com/manticore-projects/xml-doclet/commit/0826e03d6c81cc8) Manoel Campos *2025-01-28 19:56:06*

**Refactors CustomOption**

* Adds hyphen prefix if the option name doesn&#x27;t have it, 
* considering the creation of a CustomOption using 
* the of and the constructor method. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[dd080](https://github.com/manticore-projects/xml-doclet/commit/dd0804d655f0d3e) Manoel Campos *2025-01-28 19:52:53*

**Adds configuration to shadow plugin to simplify the name of the shadow jar**

* Now, executing &#x60;./gradlew shadow&#x60; will generate a build/xmldoclet-shadow.jar, 
* without version number to make easier running the doclet directly on the command line. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[44b2c](https://github.com/manticore-projects/xml-doclet/commit/44b2c3e21e07d10) Manoel Campos *2025-01-28 19:14:25*

**Fix doclet options parsing and some tests**

* &#x3D;&#x3D; CustomOption 
* - Add hyphen prefix to the option name only when creating the Doclet option. 
* The Apache Commons CLI Option doesn&#x27;t allow that on the name 
* (may the hyphen is added automatically after giving the name) 
* - Changes the CustomOption construtor to replaces a null &#x60;parameters&#x60; value 
* by an empty string, since a split is performed to extract the individual 
* option arguments (parameters) and the value cannot be null 
* - Provide a basic and general implementation of the process method. 
* That was the stopping the options to be processed. 
* The processing (validation) of each option may be different 
* and an implementation should be provided when creating 
* each option. But for now, a default implementation works as before. 
* &#x3D;&#x3D; SupportedOptions 
* - If an option has no arguments, when creating it using the Apache Commons CLI, 
* the argName method from the Option.builder() must not be called. 
* Removes the method call from newNoArgOption() method 
* and explicitly sets the number of arguments to 0 
* (otherwise, it was being set as unlimited). 
* &#x3D; AbstractTest 
* - Changes the way to get an instance of the javadoc command line tool, 
* using the javax.tools.ToolProvider instead of java.util.spi.ToolProvider. 
* The new API also provide the more specific DocumentationTool interface 
* to execute the javadoc tool, which enables 
* passing the doclet class more easily. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[66b9b](https://github.com/manticore-projects/xml-doclet/commit/66b9baa0ac1b86a) Manoel Campos *2025-01-28 19:06:55*

**Refactor SupportedOptions.newOneArgOption**

* Uses a simpler mehtod to indicate the option has a single argument. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[9ec0a](https://github.com/manticore-projects/xml-doclet/commit/9ec0a5750f03cef) Manoel Campos *2025-01-28 18:55:23*

**Adds docs to SupportedOptions**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[336b3](https://github.com/manticore-projects/xml-doclet/commit/336b3589b61830a) Manoel Campos *2025-01-28 18:53:47*

**Adds docs to CustomOption**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[02468](https://github.com/manticore-projects/xml-doclet/commit/024688c865b99ff) Manoel Campos *2025-01-28 18:47:39*

**Remove useless comment on CustomOption.getNames()**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[c79b3](https://github.com/manticore-projects/xml-doclet/commit/c79b353a05b8cec) Manoel Campos *2025-01-28 17:02:44*

**Adds constructor to SupportedOptions to populate the cliOptions attribute on class instantiation.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[7bcbf](https://github.com/manticore-projects/xml-doclet/commit/7bcbf2f9d9af6b2) Manoel Campos *2025-01-28 17:02:04*

**Use the doclet reporter object to print INFO e WARNING messages.**

* Errors messages should be handled later. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[6be81](https://github.com/manticore-projects/xml-doclet/commit/6be81ffe646394a) Manoel Campos *2025-01-28 17:00:37*

**Remove unused attribute from XmlDoclet**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[9665b](https://github.com/manticore-projects/xml-doclet/commit/9665b8d4e6b9b2f) Manoel Campos *2025-01-28 16:58:49*

**Small docs update on XmlDoclet**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[79394](https://github.com/manticore-projects/xml-doclet/commit/79394d3b3368534) Manoel Campos *2025-01-28 16:58:06*

**= Fix and refactor tests**

* - Removes huge amount of code duplication 
* &#x3D;&#x3D; Renames AbstractTestParent to AbstractTest 
* Fix and refactor the file, removing code duplication and providing new methods for test classes. 
* &#x3D;&#x3D; Fix and refactor TagTest 
* &#x3D;&#x3D; Fix and refactor InterfaceTest 
* &#x3D;&#x3D; Fix and refactor Methods Tests 
* - Split tests for each Method file inside the simpledata package into different test classes 
* - Create AbstractMethodTest class to provide utility methods to remove the huge amount of code duplication 
* &#x3D;&#x3D; Renames files Method1.java, Method2.java and Method3.java to MethodsA, MethodsB and MethodsC 
* Since files such as Method1 has methods named method1, method2 and so on, while file Method2 has methods with the same names, that was causing confusion. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[69db6](https://github.com/manticore-projects/xml-doclet/commit/69db628c6d849e6) Manoel Campos *2025-01-24 23:45:47*

**Merge pull request #4 from manticore-projects/issue3**

* JDK 21 (WIP) - Close #3 

[dba47](https://github.com/manticore-projects/xml-doclet/commit/dba47d255640ef9) manticore-projects *2025-01-14 06:18:34*

**Remove module-info.java and the configuration on build.gradle**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[05d5e](https://github.com/manticore-projects/xml-doclet/commit/05d5ed6870a6c22) Manoel Campos *2025-01-14 02:14:37*

**Removes the parseOptionName which was adding a hyphen before CLI option name,**

* that was causing the doclet instantiation to fail, 
* indicating invalid option name. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[8fea0](https://github.com/manticore-projects/xml-doclet/commit/8fea0bbd0a5615a) Manoel Campos *2025-01-14 01:52:49*

**Define version of apache commons cli**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[3667b](https://github.com/manticore-projects/xml-doclet/commit/3667b124c40c6d9) Manoel Campos *2025-01-14 01:50:37*

**Catch and log errors during doclet instantiation.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[2f06e](https://github.com/manticore-projects/xml-doclet/commit/2f06e53fa172997) Manoel Campos *2025-01-14 01:10:30*

**Fix the way to set properties inside a tasks.register call.**

* Properties such as fromRepo are now a Property object 
* that has a set method, which must be called 
* instead of assigning a value to the property directly. 
* Previously properties such as fromRepo were String, now they are Property&lt;String&gt;, 
* requiring the set() call. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[19ec2](https://github.com/manticore-projects/xml-doclet/commit/19ec2578a5c41ec) Manoel Campos *2025-01-14 00:03:14*

**Fix deprecations inside build.gradle to prepare for gradle 9**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[ff202](https://github.com/manticore-projects/xml-doclet/commit/ff202701e29c333) Manoel Campos *2025-01-14 00:00:49*

**Uses the getVersion function that was moved to buildSrc/src/main/groovy/CustomVersionTask.groovy.**

* Removes the function implementation from build.gradle 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[5f5ed](https://github.com/manticore-projects/xml-doclet/commit/5f5ed6aaff5d98a) Manoel Campos *2025-01-14 00:00:00*

**Remove stale TODO**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[35514](https://github.com/manticore-projects/xml-doclet/commit/3551447d4e3d2e7) Manoel Campos *2025-01-13 23:58:46*


## 2.0.0 (2025-01-08)

### Other changes

**Merge branch 'master' of https://github.com/manticore-projects/xml-doclet**

* # Conflicts: 
* #	build.gradle 
* #	gradle/wrapper/gradle-wrapper.properties 

[df1e3](https://github.com/manticore-projects/xml-doclet/commit/df1e3585bf79193) manticore-projects *2025-01-08 09:34:15*

**Merge pull request #2 from manoelcampos/jdk21-gradle8**

* Upgrade to JDK 21 and Gradle 8.11 (WIP) - Close #1 

[31122](https://github.com/manticore-projects/xml-doclet/commit/31122dbed91863a) manticore-projects *2025-01-08 09:19:50*

**Refactor**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[09806](https://github.com/manticore-projects/xml-doclet/commit/09806fc588a08a3) Manoel Campos *2025-01-03 23:22:03*

**Refactor**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[616f5](https://github.com/manticore-projects/xml-doclet/commit/616f571aee457eb) Manoel Campos *2025-01-03 23:21:14*

**Fix path to directory where the Java files from the XSD file are generated.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[448ea](https://github.com/manticore-projects/xml-doclet/commit/448ea6a1e31ed4c) Manoel Campos *2025-01-03 23:19:12*

**Refactor and code format**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[e66b3](https://github.com/manticore-projects/xml-doclet/commit/e66b3d2f853f128) Manoel Campos *2025-01-03 23:13:08*

**Changes comment.line_length and lineSplit spotless configuration in eclipse-java-google-style.xml to 140 characters.**

* With current high-dimension screens, 100 chars line width is too limiting. 
* Disables the join_lines_in_comments configuration, so that manual 
* breaks in comments are kept, providing more organization for documentation 
* and readability. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[cd7d1](https://github.com/manticore-projects/xml-doclet/commit/cd7d1cd6d9855b1) Manoel Campos *2025-01-03 22:58:02*

**Converts XmlDoclet javadoc to Markdown format to be more concise**

* and easier to read 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[571f0](https://github.com/manticore-projects/xml-doclet/commit/571f08f62b78386) Manoel Campos *2025-01-03 22:34:40*

**Remove TODOs**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[fae9b](https://github.com/manticore-projects/xml-doclet/commit/fae9b173a9f11aa) Manoel Campos *2025-01-03 22:23:43*

**Format TODOs**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[e5ded](https://github.com/manticore-projects/xml-doclet/commit/e5deda96629a083) Manoel Campos *2025-01-03 22:23:21*

**Fix code style issues detected by spotless plugin**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[15ed3](https://github.com/manticore-projects/xml-doclet/commit/15ed36bf1b13ec3) Manoel Campos *2025-01-03 21:39:29*

**fix checkstyle issues**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[7bc18](https://github.com/manticore-projects/xml-doclet/commit/7bc18eceb902a70) Manoel Campos *2025-01-03 21:37:25*

**Configure spotless lint plugin to just check**

* files at src/**/*.java, ignoring 
* java files generated at the /build dir 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[dd5a3](https://github.com/manticore-projects/xml-doclet/commit/dd5a3d73f61a77c) Manoel Campos *2025-01-03 21:36:38*

**Refactor XmlDoclet**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[27ba4](https://github.com/manticore-projects/xml-doclet/commit/27ba43ab2bc922f) Manoel Campos *2025-01-03 21:28:44*

**Configures xsd2java-gradle-plugin to generate Java files from XSD files**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[9f58d](https://github.com/manticore-projects/xml-doclet/commit/9f58dfa576a0e7c) Manoel Campos *2025-01-03 21:23:14*

**Fix the name of the packages where**

* the JAX-B XJC generates Java files from XSD files. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[9ea4b](https://github.com/manticore-projects/xml-doclet/commit/9ea4bf5d14dc723) Manoel Campos *2025-01-03 20:46:32*

**Update SupportedOptions to add hyphen (-) at the beginning**

* of a option/arg name given to the javadoc command line tool. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[f5c1d](https://github.com/manticore-projects/xml-doclet/commit/f5c1d8baf0c3248) Manoel Campos *2025-01-03 20:41:12*

**Updates Parser.java and TypeUtils.java**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[9ae57](https://github.com/manticore-projects/xml-doclet/commit/9ae57f3bd117528) Manoel Campos *2025-01-03 20:39:42*

**Moves methods to TypeUtils.java**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[bc338](https://github.com/manticore-projects/xml-doclet/commit/bc3389711e1eae3) Manoel Campos *2025-01-03 19:52:24*

**Updates Parser.java and XmlDoclet.java.**

* Introduces TypeUtils.java 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[9ba89](https://github.com/manticore-projects/xml-doclet/commit/9ba89231fe2980d) Manoel Campos *2025-01-03 19:48:35*

**Updates Parser.java**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[22bb9](https://github.com/manticore-projects/xml-doclet/commit/22bb9aa3c0a5b58) Manoel Campos *2024-12-27 21:57:09*

**Generates the Java files from the javadoc.xsd inside the target/generated-sources dir**

* - Those files are now ignored bu git automatically 
* - Moves javadoc.xsd to resources dir, 
* which is the default location used by org.jvnet.jaxb.jaxb-maven-plugin 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[3ec76](https://github.com/manticore-projects/xml-doclet/commit/3ec76c9bd4d7667) Manoel Campos *2024-12-27 21:51:28*

**Updates XmlDoclet version in pom.xml**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[6353b](https://github.com/manticore-projects/xml-doclet/commit/6353bebad9647f2) Manoel Campos *2024-12-27 21:44:26*

**Updates XmlDoclet to use jakarta.xml.bind imports instead of javax.xml.bind.**

* Moves inner class ClasspathResourceURIResolver to its own file. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[0eaab](https://github.com/manticore-projects/xml-doclet/commit/0eaab4b2e0d78f0) Manoel Campos *2024-12-27 21:43:49*

**Update Parser.java**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[bb270](https://github.com/manticore-projects/xml-doclet/commit/bb2704c3b59ba33) Manoel Campos *2024-12-27 21:01:51*

**Update .gitignore**

* - Adds files generated by parsing the javadoc.xsd using the JAXB maven plugin 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[12ba5](https://github.com/manticore-projects/xml-doclet/commit/12ba5128a93ba52) Manoel Campos *2024-12-27 20:54:24*

**Remove directory src/test/java/com/github/markusbernhardt/xmldoclet/simpledata/.svn**

* This is just garbage from the ancient SVN version control system. 

[9a087](https://github.com/manticore-projects/xml-doclet/commit/9a087b442b85d98) Manoel Campos *2024-12-27 20:43:26*

**Removes unused methods on XmlDoclet, which aren't neeeded anymore**

* in new JavaDoc API. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[6f1ac](https://github.com/manticore-projects/xml-doclet/commit/6f1acf29630f13b) Manoel Campos *2024-12-27 20:41:48*

**Updates XmlDoclet, Parser and CustomOption.**

* Introduces SupportedOptions class. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[01f47](https://github.com/manticore-projects/xml-doclet/commit/01f4781d7c07a44) Manoel Campos *2024-12-27 20:40:55*

**Temporarily removes test because they are not compiling after JDK 21.**


[453a9](https://github.com/manticore-projects/xml-doclet/commit/453a9bf1509a43a) Manoel Campos *2024-12-27 19:50:06*

**Update .gitignore**


[78ca2](https://github.com/manticore-projects/xml-doclet/commit/78ca26103106802) Manoel Campos *2024-12-27 19:46:16*

**Fix quotes escape in build.gradle that was causing the build to fail**

* The println was intended to show the name of a 
* build.gradle property that is not configured. 
* To show the property name without escaping, 
* we just need to print the message using single quotes. 
* This way, ${project.name} is not recognized as 
* a variable inside the quotes. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[91b44](https://github.com/manticore-projects/xml-doclet/commit/91b449632d1d366) Manoel Campos *2024-12-26 21:20:19*

**Upgrade plugin to generate shadow jar (jar with all dependencies)**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[77709](https://github.com/manticore-projects/xml-doclet/commit/777097ecc9584b6) Manoel Campos *2024-12-26 21:15:31*

**Refactor and format CustomOption to fix spotbugs reported issues.**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[777c5](https://github.com/manticore-projects/xml-doclet/commit/777c51eabccd609) Manoel Campos *2024-12-26 21:13:10*

**Adds some links for future reference**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[91e7a](https://github.com/manticore-projects/xml-doclet/commit/91e7ab72b33c07b) Manoel Campos *2024-12-26 20:53:57*

**Format build.gradle**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[b7a5b](https://github.com/manticore-projects/xml-doclet/commit/b7a5bb7c2ec1728) Manoel Campos *2024-12-26 20:02:56*

**Fix the position of / inside .gitignore entries**

* /.gradle and /build were not ignoring directories named .gradle and build, respectively. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[a7b28](https://github.com/manticore-projects/xml-doclet/commit/a7b2811f2e2f07e) Manoel Campos *2024-12-12 18:52:39*

**Update GitHub Action Workflows to JDK 21**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[a4347](https://github.com/manticore-projects/xml-doclet/commit/a434713123e9eb8) Manoel Campos *2024-12-12 18:52:39*

**Adds com.github.gradlecommunity.jaxb2 plugin dependencies**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[8b336](https://github.com/manticore-projects/xml-doclet/commit/8b336d768f53f63) Manoel Campos *2024-12-12 18:52:39*

**Initial migration to Doclet API in JDK 21**

* following the migration guide at https://docs.oracle.com/en/java/javase/21/docs/api/jdk.javadoc/jdk/javadoc/doclet/package-summary.html 
* Classes are not compiling yet. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[86341](https://github.com/manticore-projects/xml-doclet/commit/863413b4e9d3afc) Manoel Campos *2024-12-12 18:52:39*

**Update README**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[8606d](https://github.com/manticore-projects/xml-doclet/commit/8606d3a880ad969) Manoel Campos *2024-12-12 18:52:39*

**project.exec is deprecated**

* Moves getVersion() implementation to /buildSrc/main/groovy/CustomVersionTask.groovy 
* and updates build.gradle to use the new way to run a custom task. 
* Moving the function implementation makes the build.gradle simpler. 
* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[80cbf](https://github.com/manticore-projects/xml-doclet/commit/80cbf4845e14569) Manoel Campos *2024-12-12 18:52:39*

**Simplify repositories config**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[7c7e9](https://github.com/manticore-projects/xml-doclet/commit/7c7e912b25fdb14) Manoel Campos *2024-12-12 18:52:39*

**Adds jaxb-xjc updated dependency**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[732d3](https://github.com/manticore-projects/xml-doclet/commit/732d376178838b8) Manoel Campos *2024-12-12 18:52:39*

**Calls the CustomVersionTask**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[34bc1](https://github.com/manticore-projects/xml-doclet/commit/34bc12ee25acfa3) Manoel Campos *2024-12-12 18:52:39*

**Replaces deprecated project.buildDir by project.layout.buildDirectory in build.gradle**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[2c61f](https://github.com/manticore-projects/xml-doclet/commit/2c61f559762f9cc) Manoel Campos *2024-12-12 18:52:39*

**Update build.gradle docs**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[2ba54](https://github.com/manticore-projects/xml-doclet/commit/2ba5451f86bf90d) Manoel Campos *2024-12-12 18:52:39*

**Group related dependencies for organization**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[20b0f](https://github.com/manticore-projects/xml-doclet/commit/20b0f22c40ef10f) Manoel Campos *2024-12-12 18:52:39*

**Adds link to com.github.gradlecommunity.jaxb2 plugin**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[1548b](https://github.com/manticore-projects/xml-doclet/commit/1548b086104e85a) Manoel Campos *2024-12-12 18:52:39*

**Fix deprecations for spotbugsMain on build.gradle**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[01fe1](https://github.com/manticore-projects/xml-doclet/commit/01fe1dfba00b825) Manoel Campos *2024-12-12 18:52:39*

**Upgrade dependencies**

* Signed-off-by: Manoel Campos &lt;manoelcampos@gmail.com&gt; 

[24cec](https://github.com/manticore-projects/xml-doclet/commit/24cece1473ab4e9) Manoel Campos *2024-12-12 18:52:38*

**Update to gradle 8.11 to work with JDK 21**


[590c4](https://github.com/manticore-projects/xml-doclet/commit/590c4320729ec32) Manoel Campos *2024-12-12 18:52:37*

**MaxPermSize JVM arg is now MaxMetaspaceSize in current JDK versions.**


[93e13](https://github.com/manticore-projects/xml-doclet/commit/93e13d58f5572bc) Manoel Campos *2024-12-12 12:57:04*

**Update README.md**


[3e790](https://github.com/manticore-projects/xml-doclet/commit/3e7902225c17b07) manticore-projects *2023-05-22 06:42:53*

**Update README.md**


[ec451](https://github.com/manticore-projects/xml-doclet/commit/ec451b5d91bfc92) manticore-projects *2023-05-21 14:53:01*

**Update README.md**


[4b501](https://github.com/manticore-projects/xml-doclet/commit/4b501824003b6b9) manticore-projects *2023-05-21 14:51:37*


## 1.3.0 (2023-05-21)

### Features

-  Add Floating TOC ([c6374](https://github.com/manticore-projects/xml-doclet/commit/c6374f43a0e978c) Andreas Reichel)  

### Other changes


## 1.2.1 (2023-05-14)

### Features

-  Enum support ([15692](https://github.com/manticore-projects/xml-doclet/commit/156929172bf139c) Andreas Reichel)  

### Other changes


## 1.2.0 (2023-05-13)

### Features

-  transform to Restructured Text (*.rst) to use with Python Sphinx ([b869e](https://github.com/manticore-projects/xml-doclet/commit/b869e01d1a2ee59) Andreas Reichel)  

## 1.1.3 (2023-05-08)

### Other changes


## 1.1.2 (2023-05-08)

### Other changes


## 1.1.1 (2023-05-08)

### Bug Fixes

-  GitHub Deploy package ([308b2](https://github.com/manticore-projects/xml-doclet/commit/308b26cfe9f0bf4) Andreas Reichel)  
-  GitHub Deploy package ([29b09](https://github.com/manticore-projects/xml-doclet/commit/29b098d7e772886) Andreas Reichel)  

### Other changes

**Merge github.com:manticore-projects/xml-doclet**


[37771](https://github.com/manticore-projects/xml-doclet/commit/37771659bfea413) Andreas Reichel *2023-05-08 10:18:30*

**Create gradle.yml**


[cfe1d](https://github.com/manticore-projects/xml-doclet/commit/cfe1ddd8e357888) manticore-projects *2023-05-08 10:12:40*

**Create gradle-publish.yml**


[7e1f2](https://github.com/manticore-projects/xml-doclet/commit/7e1f28e462545e9) manticore-projects *2023-05-08 09:34:01*


## 1.1.0 (2023-05-08)

### Features

-  JavaDoc 11/13 support ([67bbb](https://github.com/manticore-projects/xml-doclet/commit/67bbbf0faf2871f) Andreas Reichel)  

### Other changes

**Merge pull request #13 from G-Ork/master**

* Support nested annotations #12 

[6bb0c](https://github.com/manticore-projects/xml-doclet/commit/6bb0cc1ff82b2e2) Markus Bernhardt *2018-05-28 21:52:28*

**Support nested annotations #12**


[d3cf9](https://github.com/manticore-projects/xml-doclet/commit/d3cf93772ef39e8) Georg Tsakumagos *2018-05-22 21:41:51*

**Merge pull request #11 from magwas/feature/document_test**

* Feature/document test 

[f272e](https://github.com/manticore-projects/xml-doclet/commit/f272e76fe694ee1) Markus Bernhardt *2017-01-27 00:04:35*

**newline needed**


[1c6ca](https://github.com/manticore-projects/xml-doclet/commit/1c6ca7644cb150a) Árpád Magosányi *2017-01-20 12:28:57*

**document how to get test code xml. closes #10**


[fb39c](https://github.com/manticore-projects/xml-doclet/commit/fb39c4fa63d8614) Árpád Magosányi *2017-01-20 12:27:52*

**Merge pull request #9 from magwas/master**

* update pom example, mention maven command line. closes #8 

[3af32](https://github.com/manticore-projects/xml-doclet/commit/3af32812d01092b) Markus Bernhardt *2017-01-05 16:02:53*

**update pom example, mention maven command line. closes #8**


[67cac](https://github.com/manticore-projects/xml-doclet/commit/67cacfe5b747d6c) Árpád Magosányi *2017-01-05 15:46:41*

**Release 1.0.5**


[31f2d](https://github.com/manticore-projects/xml-doclet/commit/31f2d3290deb32c) Markus Bernhardt *2015-09-15 12:58:46*

**[maven-release-plugin] prepare for next development iteration**


[95188](https://github.com/manticore-projects/xml-doclet/commit/95188f38d622d92) Markus Bernhardt *2015-09-15 12:50:20*


## xml-doclet-1.0.5 (2015-09-15)

### Other changes

**[maven-release-plugin] prepare release xml-doclet-1.0.5**


[2328f](https://github.com/manticore-projects/xml-doclet/commit/2328f9d2b69741b) Markus Bernhardt *2015-09-15 12:50:15*

**Prepare release**


[259ba](https://github.com/manticore-projects/xml-doclet/commit/259bad38e62c420) Markus Bernhardt *2015-09-15 12:10:24*

**Prepare release**


[b4be0](https://github.com/manticore-projects/xml-doclet/commit/b4be0f893563856) Markus Bernhardt *2015-09-15 11:37:10*

**Prepare release**


[2909a](https://github.com/manticore-projects/xml-doclet/commit/2909afcfa18ebea) Markus Bernhardt *2015-09-15 11:34:21*

**SNAPSHOT**


[a5ce0](https://github.com/manticore-projects/xml-doclet/commit/a5ce05654b27c92) Markus Bernhardt *2015-09-15 11:22:33*

**Added Javadoc to avoid errors and warnings**


[5e591](https://github.com/manticore-projects/xml-doclet/commit/5e5910047ade1ee) Markus Bernhardt *2015-09-15 11:20:50*

**Maven plugin versions update**


[59759](https://github.com/manticore-projects/xml-doclet/commit/5975986799d34df) Markus Bernhardt *2015-09-15 09:04:06*

**Merge pull request #6 from sandeshh/master**

* Added the support for reading javadoc of fields in an Interface. 
* Thanks for your work! 

[a8bfa](https://github.com/manticore-projects/xml-doclet/commit/a8bfae624790cfb) Markus Bernhardt *2015-09-15 08:17:58*

**Added support for fields in an Interface**


[54358](https://github.com/manticore-projects/xml-doclet/commit/543588076e18afc) sandeshh *2015-09-15 01:23:27*

**Release 1.0.4**


[f3548](https://github.com/manticore-projects/xml-doclet/commit/f3548244ed9f6a5) Markus Bernhardt *2014-01-22 21:06:55*

**[maven-release-plugin] prepare for next development iteration**


[9563b](https://github.com/manticore-projects/xml-doclet/commit/9563b89bd45813f) Markus Bernhardt *2014-01-22 20:51:13*


## xml-doclet-1.0.4 (2014-01-22)

### Other changes

**[maven-release-plugin] prepare release xml-doclet-1.0.4**


[ba4c1](https://github.com/manticore-projects/xml-doclet/commit/ba4c12c11515ed5) Markus Bernhardt *2014-01-22 20:51:06*

**Merge pull request #5 from jussimalinen/master**

* I never realized I&#x27;m using this 1.7 syntax there anyway. 
* Thanks for that patch. 

[57308](https://github.com/manticore-projects/xml-doclet/commit/57308d8e20132e3) Markus Bernhardt *2014-01-22 20:41:05*

**Make code 1.6 compatible and change target to 1.6**


[57232](https://github.com/manticore-projects/xml-doclet/commit/572328c46ad7749) Jussi Malinen *2014-01-22 17:51:57*

**Merge branch 'master' of git@github.com:MarkusBernhardt/xml-doclet.git**


[bbe5b](https://github.com/manticore-projects/xml-doclet/commit/bbe5b3954513e6a) Markus Bernhardt *2013-11-01 11:01:02*

**Requirement of Oracle JDK 7 added.**


[25ccc](https://github.com/manticore-projects/xml-doclet/commit/25ccca9533252ed) Markus Bernhardt *2013-11-01 10:59:50*

**[maven-release-plugin] prepare for next development iteration**


[c7bad](https://github.com/manticore-projects/xml-doclet/commit/c7badd48c1c2805) Markus Bernhardt *2013-10-31 00:35:55*


## xml-doclet-1.0.3 (2013-10-31)

### Other changes

**[maven-release-plugin] prepare release xml-doclet-1.0.3**


[21ecf](https://github.com/manticore-projects/xml-doclet/commit/21ecf1c58a7fc2b) Markus Bernhardt *2013-10-31 00:35:50*

**Removed all references to log4j**


[821b5](https://github.com/manticore-projects/xml-doclet/commit/821b5fcdbfce68f) Markus Bernhardt *2013-10-31 00:34:52*

**[maven-release-plugin] prepare for next development iteration**


[97bba](https://github.com/manticore-projects/xml-doclet/commit/97bba262d6b2864) Markus Bernhardt *2013-10-30 21:13:57*


## xml-doclet-1.0.2 (2013-10-30)

### Other changes

**[maven-release-plugin] prepare release xml-doclet-1.0.2**


[0882c](https://github.com/manticore-projects/xml-doclet/commit/0882c0e7afa6bde) Markus Bernhardt *2013-10-30 21:13:52*

**Changed the scope of the jar-with-dependencies to 'test'.**


[97734](https://github.com/manticore-projects/xml-doclet/commit/977348ec5fdc450) Markus Bernhardt *2013-10-30 21:09:11*

**Changed the scope of the jar-with-dependencies to 'test'.**


[8c45c](https://github.com/manticore-projects/xml-doclet/commit/8c45c83f0d2dc66) Markus Bernhardt *2013-10-30 21:08:49*

**README**


[3063f](https://github.com/manticore-projects/xml-doclet/commit/3063f43fea47327) Markus Bernhardt *2013-10-20 21:59:33*

**README**


[d8def](https://github.com/manticore-projects/xml-doclet/commit/d8def527a8f371f) Markus Bernhardt *2013-10-20 21:55:07*

**README**


[028a1](https://github.com/manticore-projects/xml-doclet/commit/028a140692ded55) Markus Bernhardt *2013-10-20 21:40:48*

**README**


[46b30](https://github.com/manticore-projects/xml-doclet/commit/46b30bc25c25672) Markus Bernhardt *2013-10-19 20:21:45*

**Release 1.0.1**


[f4bbb](https://github.com/manticore-projects/xml-doclet/commit/f4bbb611d7daa57) Markus Bernhardt *2013-10-18 14:25:46*

**Updated usage section in README.md**


[74340](https://github.com/manticore-projects/xml-doclet/commit/74340a84b29ac37) Markus Bernhardt *2013-10-18 14:07:10*

**[maven-release-plugin] prepare for next development iteration**


[22280](https://github.com/manticore-projects/xml-doclet/commit/22280ad78932324) Markus Bernhardt *2013-10-18 13:56:27*


## xml-doclet-1.0.1 (2013-10-18)

### Other changes

**[maven-release-plugin] prepare release xml-doclet-1.0.1**


[3f719](https://github.com/manticore-projects/xml-doclet/commit/3f7192d8164c75d) Markus Bernhardt *2013-10-18 13:56:22*

**TagTest added**


[59dce](https://github.com/manticore-projects/xml-doclet/commit/59dce0001df74f7) Markus Bernhardt *2013-10-18 13:54:19*

**Added Javadoc tags**


[c5d78](https://github.com/manticore-projects/xml-doclet/commit/c5d788e9d4d5a38) Markus Bernhardt *2013-10-18 13:28:36*

**[maven-release-plugin] prepare for next development iteration**


[4aaee](https://github.com/manticore-projects/xml-doclet/commit/4aaeebbd6be1323) Markus Bernhardt *2013-10-17 17:16:18*


## xml-doclet-1.0.0 (2013-10-17)

### Other changes

**[maven-release-plugin] prepare release xml-doclet-1.0.0**


[6f50f](https://github.com/manticore-projects/xml-doclet/commit/6f50fd5b9a262ae) Markus Bernhardt *2013-10-17 17:16:14*

**Prepare first release**


[ecdba](https://github.com/manticore-projects/xml-doclet/commit/ecdba9cd4051570) Markus Bernhardt *2013-10-17 17:14:31*

**Build fat jar**


[31039](https://github.com/manticore-projects/xml-doclet/commit/3103940efb31927) Markus Bernhardt *2013-10-17 17:14:02*

**Dependencies**


[39892](https://github.com/manticore-projects/xml-doclet/commit/39892da5afcc003) Markus Bernhardt *2013-10-17 16:48:42*

**Fixing Eclipse warnings in unit test sources**


[dd07d](https://github.com/manticore-projects/xml-doclet/commit/dd07d52f0c58713) Markus Bernhardt *2013-10-17 16:46:38*

**Organize imports**


[f5734](https://github.com/manticore-projects/xml-doclet/commit/f5734e057a2f0c1) Markus Bernhardt *2013-10-17 16:27:54*

**Format code**


[5af48](https://github.com/manticore-projects/xml-doclet/commit/5af4867d55ba595) Markus Bernhardt *2013-10-17 16:27:10*

**Prepare initial release**


[565c3](https://github.com/manticore-projects/xml-doclet/commit/565c39d3eced53b) Markus Bernhardt *2013-10-17 16:25:05*

**Fixed small warning**


[55b21](https://github.com/manticore-projects/xml-doclet/commit/55b217e938737ac) Markus Bernhardt *2013-10-17 16:22:53*

**MethodTest added**


[11d7c](https://github.com/manticore-projects/xml-doclet/commit/11d7c1a8f2033fa) Markus Bernhardt *2013-10-17 16:22:22*

**InterfaceTest added**


[5c4e4](https://github.com/manticore-projects/xml-doclet/commit/5c4e42a4cf174b5) Markus Bernhardt *2013-10-17 15:13:01*

**FieldTest added**


[17b4e](https://github.com/manticore-projects/xml-doclet/commit/17b4e2c3a51542a) Markus Bernhardt *2013-10-17 14:49:27*

**EnumTest added**


[3115a](https://github.com/manticore-projects/xml-doclet/commit/3115a6bfa7f6880) Markus Bernhardt *2013-10-17 14:02:56*

**ClassTest added**


[0db9e](https://github.com/manticore-projects/xml-doclet/commit/0db9ea68733fd8b) Markus Bernhardt *2013-10-17 10:48:15*

**Don't set empty comment**


[cf333](https://github.com/manticore-projects/xml-doclet/commit/cf333bc1a6ee2f4) Markus Bernhardt *2013-10-17 09:13:14*

**First unit test works.**


[8da02](https://github.com/manticore-projects/xml-doclet/commit/8da02f18ce4f87b) Markus Bernhardt *2013-10-17 09:07:07*

**First real unit test. Failing.**


[437ba](https://github.com/manticore-projects/xml-doclet/commit/437ba49b3b8082a) Markus Bernhardt *2013-10-16 23:01:15*

**-dryrun option added**


[cf83d](https://github.com/manticore-projects/xml-doclet/commit/cf83dd25bdb92ec) Markus Bernhardt *2013-10-16 22:21:51*

**Marshalling to file works**


[81da1](https://github.com/manticore-projects/xml-doclet/commit/81da1a36bc43604) Markus Bernhardt *2013-10-16 22:06:18*

**Added command line parsing**


[89e6e](https://github.com/manticore-projects/xml-doclet/commit/89e6e7219f9d122) Markus Bernhardt *2013-10-16 18:07:50*

**Fix unit test logging**


[ea3d2](https://github.com/manticore-projects/xml-doclet/commit/ea3d2b5d378e949) Markus Bernhardt *2013-10-15 23:28:10*

**First primitive unit test**


[e4af7](https://github.com/manticore-projects/xml-doclet/commit/e4af70b55c5ddd2) Markus Bernhardt *2013-10-15 23:07:23*

**Started with Cli**


[b1b3b](https://github.com/manticore-projects/xml-doclet/commit/b1b3b5264b00955) Markus Bernhardt *2013-10-15 16:45:55*

**Readme**


[5268d](https://github.com/manticore-projects/xml-doclet/commit/5268da85843d22a) Markus Bernhardt *2013-10-15 06:43:48*

**Class finished**


[0e0ab](https://github.com/manticore-projects/xml-doclet/commit/0e0abcc8438bb11) Markus Bernhardt *2013-10-14 22:43:51*

**Interface finished**


[bc6c3](https://github.com/manticore-projects/xml-doclet/commit/bc6c3ecee813c37) Markus Bernhardt *2013-10-14 21:58:55*

**Enum finished**


[a431c](https://github.com/manticore-projects/xml-doclet/commit/a431c8d8f5c35a4) Markus Bernhardt *2013-10-14 17:16:50*

**Annotations finished**


[3aea9](https://github.com/manticore-projects/xml-doclet/commit/3aea9344422225b) Markus Bernhardt *2013-10-14 00:06:08*

**Initial commit**


[34dbc](https://github.com/manticore-projects/xml-doclet/commit/34dbc6ee53bce37) Markus Bernhardt *2013-10-13 22:52:19*

**Initial commit**


[6958c](https://github.com/manticore-projects/xml-doclet/commit/6958ca931d2b728) Markus Bernhardt *2013-10-13 12:10:55*


