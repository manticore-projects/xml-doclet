
.. raw:: html

    <div id="floating-toc">
        <div class="search-container">
            <input type="button" id="toc-hide-show-btn"></input>
            <input type="text" id="toc-search" placeholder="Search" />
        </div>
        <ul id="toc-list"></ul>
    </div>



#######################################################################
XmlDoclet API 2.2.0-SNAPSHOT
#######################################################################

Base Package: com.manticore.tools.xmldoclet


..  _com.manticore.tools.xmldoclet:
***********************************************************************
Base
***********************************************************************

..  _com.manticore.tools.xmldoclet.CustomOption:

=======================================================================
CustomOption
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`Option<jdk.javadoc.doclet.Doclet.Option>` 


                Creates an Option with a given specification and a default arguments processor.
                
                |          :ref:`String<java.lang.String>` name

                |          :ref:`String<java.lang.String>` description

                |          :ref:`String<java.lang.String>` argName

                |          int argumentCount

            
                Creates an Option with a given specification.
                |          :ref:`String<java.lang.String>` name

                |          :ref:`String<java.lang.String>` description

                |          :ref:`String<java.lang.String>` parameters

                |          int argumentCount

                |          :ref:`String>><java.util.function.BiPredicate<java.lang.String,java.util.List<java.lang.String>>>` argumentsProcessor

            | **newOneArg** (name, description, argName, argumentsProcessor) → :ref:`CustomOption<com.manticore.tools.xmldoclet.CustomOption>`
| Creates an Option with a single argument value and a given specification.
|          :ref:`String<java.lang.String>` name
|          :ref:`String<java.lang.String>` description
|          :ref:`String<java.lang.String>` argName
|          :ref:`String>><java.util.function.BiPredicate<java.lang.String,java.util.List<java.lang.String>>>` argumentsProcessor
|          returns :ref:`CustomOption<com.manticore.tools.xmldoclet.CustomOption>`



| **newNoArgs** (name, description, argumentsProcessor) → :ref:`CustomOption<com.manticore.tools.xmldoclet.CustomOption>`
| Creates an Option with no arguments and a given specification
|          :ref:`String<java.lang.String>` name
|          :ref:`String<java.lang.String>` description
|          :ref:`String>><java.util.function.BiPredicate<java.lang.String,java.util.List<java.lang.String>>>` argumentsProcessor
|          returns :ref:`CustomOption<com.manticore.tools.xmldoclet.CustomOption>`




                |          :ref:`String<java.lang.String>` name

                |          returns :ref:`String<java.lang.String>`


            | *@Override*
| **getArgumentCount** () → int
|          returns int



| *@Override*
| **getDescription** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getKind** () → :ref:`Kind<jdk.javadoc.doclet.Doclet.Option.Kind>`
|          returns :ref:`Kind<jdk.javadoc.doclet.Doclet.Option.Kind>`



| *@Override*
| **getNames** () → :ref:`String><java.util.List<java.lang.String>>`
| {@inheritDoc}, In the case of this class, the list has only one element, the single option name.
|          returns :ref:`String><java.util.List<java.lang.String>>`



| **getName** () → :ref:`String<java.lang.String>`
| This class provides a single name for the option. Therefore, no alternative names are supported.
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getParameters** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **process** (option, arguments) → boolean
| {@inheritDoc}, It must check if the given option arguments are valid.
|          :ref:`String<java.lang.String>` option
|          :ref:`String><java.util.List<java.lang.String>>` arguments
|          returns boolean




..  _com.manticore.tools.xmldoclet.Parser:

=======================================================================
Parser
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| The main parser class. It scans the given Doclet document root and creates the XML tree.

| **Parser** (env)
|          :ref:`DocletEnvironment<jdk.javadoc.doclet.DocletEnvironment>` env


| **getClasses** (env) → :ref:`TypeElement><java.util.Set<javax.lang.model.element.TypeElement>>`
|          :ref:`DocletEnvironment<jdk.javadoc.doclet.DocletEnvironment>` env
|          returns :ref:`TypeElement><java.util.Set<javax.lang.model.element.TypeElement>>`




                Gets the JavaDoc comment for an element.
 
 This method retrieves the DocCommentTree for an element and returns
 its full body as a string. It also post-processes the comment to fix
 an issue where HTML entities and tags get commas added around them.
 The regex replacements remove these unwanted commas to ensure the
 HTML content is properly preserved in the generated XML.
                
                
                |          :ref:`Element<javax.lang.model.element.Element>` element

                |          returns :ref:`String<java.lang.String>`


            | **getTags** (element) → :ref:`DocTree><java.util.List<? extends com.sun.source.doctree.DocTree>>`
|          :ref:`Element<javax.lang.model.element.Element>` element
|          returns :ref:`DocTree><java.util.List<? extends com.sun.source.doctree.DocTree>>`



| **parseRootDoc** () → :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>`
| The entry point into parsing the javadoc.
|          returns :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>`




                
                
                
                |          :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>` rootNode

                |          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classElement

                |          returns :ref:`Package<com.manticore.tools.xmldoclet.xjc.Package>`


            
                {@return the top-level class of a given inner class, or the class itself if it's not an inner class}
                
                |          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classElement

                |          returns :ref:`TypeElement<javax.lang.model.element.TypeElement>`


            
                |          :ref:`PackageElement<javax.lang.model.element.PackageElement>` packageDoc

                |          returns :ref:`Package<com.manticore.tools.xmldoclet.xjc.Package>`


            
                Parse the JavaDoc of an annotation type.
                
                
                |          :ref:`TypeElement<javax.lang.model.element.TypeElement>` annotationTypeDoc

                |          returns :ref:`Annotation<com.manticore.tools.xmldoclet.xjc.Annotation>`


            
                Parse the elements of an annotation
                
                
                |          :ref:`ExecutableElement<javax.lang.model.element.ExecutableElement>` annotationTypeElementDoc

                |          returns :ref:`AnnotationElement<com.manticore.tools.xmldoclet.xjc.AnnotationElement>`


            
                |          :ref:`VariableElement<javax.lang.model.element.VariableElement>` element

                |          returns :ref:`String<java.lang.String>`


            
                |          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classDoc

                |          returns :ref:`Enum<com.manticore.tools.xmldoclet.xjc.Enum>`


            
                Parses an enum type definition
                
                
                |          :ref:`VariableElement<javax.lang.model.element.VariableElement>` fieldDoc

                |          returns :ref:`EnumConstant<com.manticore.tools.xmldoclet.xjc.EnumConstant>`


            
                |          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classDoc

                |          returns :ref:`Interface<com.manticore.tools.xmldoclet.xjc.Interface>`


            
                |          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classDoc

                |          returns :ref:`Class<com.manticore.tools.xmldoclet.xjc.Class>`


            
                |          :ref:`ExecutableElement<javax.lang.model.element.ExecutableElement>` constructorDoc

                |          returns :ref:`Constructor<com.manticore.tools.xmldoclet.xjc.Constructor>`


            
                |          :ref:`ExecutableElement<javax.lang.model.element.ExecutableElement>` methodDoc

                |          returns :ref:`Method<com.manticore.tools.xmldoclet.xjc.Method>`


            
                |          :ref:`VariableElement<javax.lang.model.element.VariableElement>` parameter

                |          returns :ref:`MethodParameter<com.manticore.tools.xmldoclet.xjc.MethodParameter>`


            
                |          :ref:`VariableElement<javax.lang.model.element.VariableElement>` fieldDoc

                |          returns :ref:`Field<com.manticore.tools.xmldoclet.xjc.Field>`


            
                |          :ref:`WildcardType<javax.lang.model.type.WildcardType>` wildcard

                |          returns :ref:`Wildcard<com.manticore.tools.xmldoclet.xjc.Wildcard>`


            
                |          :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>` wildcardNode

                |          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` extendType

                |          returns void


            
                |          :ref:`TypeParameterElement<javax.lang.model.element.TypeParameterElement>` typeParameter

                |          returns :ref:`TypeParameter<com.manticore.tools.xmldoclet.xjc.TypeParameter>`


            
                Parse type variables for generics
                
                
                |          :ref:`TypeVariable<javax.lang.model.type.TypeVariable>` typeVariable

                |          returns :ref:`TypeParameter<com.manticore.tools.xmldoclet.xjc.TypeParameter>`


            
                Gets a type parameter bound for a generic type (such as &lt;T extends Number&gt; or &lt;T extends Comparable&lt;E&gt; &amp; Serializable&gt;)
 and splits the name of each type into a list of strings
                
                
                |          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` bound

                |          returns :ref:`String><java.util.List<java.lang.String>>`


            
                |          :ref:`DocTree<com.sun.source.doctree.DocTree>` tagDoc

                |          returns :ref:`TagInfo<com.manticore.tools.xmldoclet.xjc.TagInfo>`


            
                
                
                |          :ref:`Element<javax.lang.model.element.Element>` doc

                |          returns :ref:`String<java.lang.String>`


            
                Parses a ,{@link TypeMirror}, into a ,{@link TypeInfo}, object used by the XmlDoclet.
                
                
                |          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` type

                |          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`


            
..  _com.manticore.tools.xmldoclet.TypeUtils:

=======================================================================
TypeUtils
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| **TypeUtils** (types, elements)
|          :ref:`Types<javax.lang.model.util.Types>` types
|          :ref:`Elements<javax.lang.model.util.Elements>` elements


| **getMethodSignature** (methodDoc) → :ref:`String<java.lang.String>`
|          :ref:`ExecutableElement<javax.lang.model.element.ExecutableElement>` methodDoc
|          returns :ref:`String<java.lang.String>`



| **hasModifier** (element, modifier) → boolean
| Checks if an element has a given modifier
|          :ref:`Element<javax.lang.model.element.Element>` element
|          :ref:`Modifier<javax.lang.model.element.Modifier>` modifier
|          returns boolean



| **getFields** (classElement) → :ref:`VariableElement><java.util.List<javax.lang.model.element.VariableElement>>`
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classElement
|          returns :ref:`VariableElement><java.util.List<javax.lang.model.element.VariableElement>>`



| **getConstructors** (classElement) → :ref:`ExecutableElement><java.util.List<javax.lang.model.element.ExecutableElement>>`
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classElement
|          returns :ref:`ExecutableElement><java.util.List<javax.lang.model.element.ExecutableElement>>`



| **getMethods** (classElement) → :ref:`ExecutableElement><java.util.List<javax.lang.model.element.ExecutableElement>>`
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classElement
|          returns :ref:`ExecutableElement><java.util.List<javax.lang.model.element.ExecutableElement>>`



| **getWildcardType** (typeMirror) → :ref:`WildcardType<javax.lang.model.type.WildcardType>`
|          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` typeMirror
|          returns :ref:`WildcardType<javax.lang.model.type.WildcardType>`



| **getParameterizedType** (typeMirror) → :ref:`DeclaredType<javax.lang.model.type.DeclaredType>`
| Gets a type as DeclaredType if the typeMirror has type arguments (such a ,`List``String```,).
|          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` typeMirror
|          returns :ref:`DeclaredType<javax.lang.model.type.DeclaredType>`



| **isArray** (typeMirror) → boolean
|          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` typeMirror
|          returns boolean



| **getArrayDimension** (typeMirror) → :ref:`String<java.lang.String>`
|          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` typeMirror
|          returns :ref:`String<java.lang.String>`




                |          :ref:`Element<javax.lang.model.element.Element>` element

                |          returns :ref:`String<java.lang.String>`


            
                |          :ref:`TypeMirror<javax.lang.model.type.TypeMirror>` typeMirror

                |          returns :ref:`String<java.lang.String>`


            | **getEnumConstants** (enumTypeElement) → :ref:`VariableElement><java.util.List<javax.lang.model.element.VariableElement>>`
| Gets the enum constants from a TypeElement that represents an enum type.
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` enumTypeElement
|          returns :ref:`VariableElement><java.util.List<javax.lang.model.element.VariableElement>>`



| **isInnerClass** (classElement) → boolean
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` classElement
|          returns boolean



| **isException** (typeElement) → boolean
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` typeElement
|          returns boolean



| **isError** (typeElement) → boolean
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` typeElement
|          returns boolean



| **isSerializable** (typeElement) → boolean
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` typeElement
|          returns boolean



| **isExternalizable** (typeElement) → boolean
|          :ref:`TypeElement<javax.lang.model.element.TypeElement>` typeElement
|          returns boolean




..  _com.manticore.tools.xmldoclet.XmlDoclet:

=======================================================================
XmlDoclet
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` *implements:* :ref:`Doclet<jdk.javadoc.doclet.Doclet>` 

| **XmlDoclet** ()


| *@Override*
| **init** (locale, reporter)
|          :ref:`Locale<java.util.Locale>` locale
|          :ref:`Reporter<jdk.javadoc.doclet.Reporter>` reporter


| *@Override*
| **getName** () → :ref:`String<java.lang.String>`
|          returns :ref:`String<java.lang.String>`



| *@Override*
| **getSupportedOptions** () → :ref:`CustomOption><java.util.Set<? extends com.manticore.tools.xmldoclet.CustomOption>>`
|          returns :ref:`CustomOption><java.util.Set<? extends com.manticore.tools.xmldoclet.CustomOption>>`



| *@Override*
| **getSupportedSourceVersion** () → :ref:`SourceVersion<javax.lang.model.SourceVersion>`
|          returns :ref:`SourceVersion<javax.lang.model.SourceVersion>`



| *@Override*
| **run** (env) → boolean
| Processes the JavaDoc documentation. This method is required for all doclets.
|          :ref:`DocletEnvironment<jdk.javadoc.doclet.DocletEnvironment>` env
|          returns boolean



| **transform** (xsltInputStream, xmlFile, outFile, parameters)
|          :ref:`InputStream<java.io.InputStream>` xsltInputStream
|          :ref:`File<java.io.File>` xmlFile
|          :ref:`File<java.io.File>` outFile
|          :ref:`String><java.util.Map<java.lang.String,java.lang.String>>` parameters


| **save** (root)
| Save XML object model to a file via JAXB.
|          :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>` root


| **getRoot** () → :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>`
|          returns :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>`




..  _com.manticore.tools.xmldoclet.xjc:
***********************************************************************
xjc
***********************************************************************

..  _com.manticore.tools.xmldoclet.xjc.Annotation:

=======================================================================
Annotation
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for annotation complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="annotation"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="element" type="{}annotationElement" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="scope" type="{}scope"`` ``attribute name="included" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Annotation** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getElement** () → :ref:`AnnotationElement><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationElement>>`
| Gets the value of the element property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the element property. 
| For example, to add a new item, do as follows: `getElement().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationElement`,
|          returns :ref:`AnnotationElement><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationElement>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getScope** () → :ref:`String<java.lang.String>`
| Gets the value of the scope property.
|          returns :ref:`String<java.lang.String>`



| **setScope** (value)
| Sets the value of the scope property.
|          :ref:`String<java.lang.String>` value


| **isIncluded** () → boolean
| Gets the value of the included property.
|          returns boolean



| **setIncluded** (value)
| Sets the value of the included property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.AnnotationArgument:

=======================================================================
AnnotationArgument
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for annotationArgument complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="annotationArgument"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="type" type="{}typeInfo" minOccurs="0"`` ``choice`` ``element name="value" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/choice`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="primitive" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="array" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **AnnotationArgument** ()


| **getType** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Gets the value of the type property.
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **setType** (value)
| Sets the value of the type property.
|          :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>` value


| **getValue** () → :ref:`String><java.util.List<java.lang.String>>`
| Gets the value of the value property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the value property. 
| For example, to add a new item, do as follows: `getValue().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`String`,
|          returns :ref:`String><java.util.List<java.lang.String>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **isPrimitive** () → boolean
| Gets the value of the primitive property.
|          returns boolean



| **setPrimitive** (value)
| Sets the value of the primitive property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isArray** () → boolean
| Gets the value of the array property.
|          returns boolean



| **setArray** (value)
| Sets the value of the array property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.AnnotationElement:

=======================================================================
AnnotationElement
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for annotationElement complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="annotationElement"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="type" type="{}typeInfo" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="default" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **AnnotationElement** ()


| **getType** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Gets the value of the type property.
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **setType** (value)
| Sets the value of the type property.
|          :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>` value


| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getDefault** () → :ref:`String<java.lang.String>`
| Gets the value of the default property.
|          returns :ref:`String<java.lang.String>`



| **setDefault** (value)
| Sets the value of the default property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.AnnotationInstance:

=======================================================================
AnnotationInstance
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for annotationInstance complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="annotationInstance"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="argument" type="{}annotationArgument" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **AnnotationInstance** ()


| **getArgument** () → :ref:`AnnotationArgument><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationArgument>>`
| Gets the value of the argument property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the argument property. 
| For example, to add a new item, do as follows: `getArgument().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationArgument`,
|          returns :ref:`AnnotationArgument><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationArgument>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.Class:

=======================================================================
Class
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for class complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="class"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="generic" type="{}typeParameter" maxOccurs="unbounded" minOccurs="0"`` ``element name="class" type="{}typeInfo" minOccurs="0"`` ``element name="interface" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="constructor" type="{}constructor" maxOccurs="unbounded" minOccurs="0"`` ``element name="method" type="{}method" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``element name="field" type="{}field" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="scope" type="{}scope"`` ``attribute name="abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="error" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="exception" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="externalizable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="included" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true"`` ``attribute name="serializable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Class** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getGeneric** () → :ref:`TypeParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeParameter>>`
| Gets the value of the generic property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the generic property. 
| For example, to add a new item, do as follows: `getGeneric().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeParameter`,
|          returns :ref:`TypeParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeParameter>>`



| **getClazz** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Gets the value of the clazz property.
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **setClazz** (value)
| Sets the value of the clazz property.
|          :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>` value


| **getInterface** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the interface property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the interface property. 
| For example, to add a new item, do as follows: `getInterface().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



| **getConstructor** () → :ref:`Constructor><java.util.List<com.manticore.tools.xmldoclet.xjc.Constructor>>`
| Gets the value of the constructor property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the constructor property. 
| For example, to add a new item, do as follows: `getConstructor().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Constructor`,
|          returns :ref:`Constructor><java.util.List<com.manticore.tools.xmldoclet.xjc.Constructor>>`



| **getMethod** () → :ref:`Method><java.util.List<com.manticore.tools.xmldoclet.xjc.Method>>`
| Gets the value of the method property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the method property. 
| For example, to add a new item, do as follows: `getMethod().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Method`,
|          returns :ref:`Method><java.util.List<com.manticore.tools.xmldoclet.xjc.Method>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getField** () → :ref:`Field><java.util.List<com.manticore.tools.xmldoclet.xjc.Field>>`
| Gets the value of the field property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the field property. 
| For example, to add a new item, do as follows: `getField().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Field`,
|          returns :ref:`Field><java.util.List<com.manticore.tools.xmldoclet.xjc.Field>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getScope** () → :ref:`String<java.lang.String>`
| Gets the value of the scope property.
|          returns :ref:`String<java.lang.String>`



| **setScope** (value)
| Sets the value of the scope property.
|          :ref:`String<java.lang.String>` value


| **isAbstract** () → boolean
| Gets the value of the abstract property.
|          returns boolean



| **setAbstract** (value)
| Sets the value of the abstract property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isError** () → boolean
| Gets the value of the error property.
|          returns boolean



| **setError** (value)
| Sets the value of the error property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isException** () → boolean
| Gets the value of the exception property.
|          returns boolean



| **setException** (value)
| Sets the value of the exception property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isExternalizable** () → boolean
| Gets the value of the externalizable property.
|          returns boolean



| **setExternalizable** (value)
| Sets the value of the externalizable property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isIncluded** () → boolean
| Gets the value of the included property.
|          returns boolean



| **setIncluded** (value)
| Sets the value of the included property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isSerializable** () → boolean
| Gets the value of the serializable property.
|          returns boolean



| **setSerializable** (value)
| Sets the value of the serializable property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.Constructor:

=======================================================================
Constructor
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for constructor complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="constructor"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="parameter" type="{}methodParameter" maxOccurs="unbounded" minOccurs="0"`` ``element name="exception" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="signature" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="scope" type="{}scope"`` ``attribute name="final" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="included" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true"`` ``attribute name="native" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="synchronized" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="static" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="varArgs" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Constructor** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getParameter** () → :ref:`MethodParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.MethodParameter>>`
| Gets the value of the parameter property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the parameter property. 
| For example, to add a new item, do as follows: `getParameter().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`MethodParameter`,
|          returns :ref:`MethodParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.MethodParameter>>`



| **getException** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the exception property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the exception property. 
| For example, to add a new item, do as follows: `getException().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getSignature** () → :ref:`String<java.lang.String>`
| Gets the value of the signature property.
|          returns :ref:`String<java.lang.String>`



| **setSignature** (value)
| Sets the value of the signature property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getScope** () → :ref:`String<java.lang.String>`
| Gets the value of the scope property.
|          returns :ref:`String<java.lang.String>`



| **setScope** (value)
| Sets the value of the scope property.
|          :ref:`String<java.lang.String>` value


| **isFinal** () → boolean
| Gets the value of the final property.
|          returns boolean



| **setFinal** (value)
| Sets the value of the final property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isIncluded** () → boolean
| Gets the value of the included property.
|          returns boolean



| **setIncluded** (value)
| Sets the value of the included property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isNative** () → boolean
| Gets the value of the native property.
|          returns boolean



| **setNative** (value)
| Sets the value of the native property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isSynchronized** () → boolean
| Gets the value of the synchronized property.
|          returns boolean



| **setSynchronized** (value)
| Sets the value of the synchronized property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isStatic** () → boolean
| Gets the value of the static property.
|          returns boolean



| **setStatic** (value)
| Sets the value of the static property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isVarArgs** () → boolean
| Gets the value of the varArgs property.
|          returns boolean



| **setVarArgs** (value)
| Sets the value of the varArgs property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.Enum:

=======================================================================
Enum
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for enum complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="enum"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="class" type="{}typeInfo" minOccurs="0"`` ``element name="interface" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="constant" type="{}enumConstant" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="scope" type="{}scope"`` ``attribute name="included" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Enum** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getClazz** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Gets the value of the clazz property.
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **setClazz** (value)
| Sets the value of the clazz property.
|          :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>` value


| **getInterface** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the interface property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the interface property. 
| For example, to add a new item, do as follows: `getInterface().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



| **getConstant** () → :ref:`EnumConstant><java.util.List<com.manticore.tools.xmldoclet.xjc.EnumConstant>>`
| Gets the value of the constant property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the constant property. 
| For example, to add a new item, do as follows: `getConstant().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`EnumConstant`,
|          returns :ref:`EnumConstant><java.util.List<com.manticore.tools.xmldoclet.xjc.EnumConstant>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getScope** () → :ref:`String<java.lang.String>`
| Gets the value of the scope property.
|          returns :ref:`String<java.lang.String>`



| **setScope** (value)
| Sets the value of the scope property.
|          :ref:`String<java.lang.String>` value


| **isIncluded** () → boolean
| Gets the value of the included property.
|          returns boolean



| **setIncluded** (value)
| Sets the value of the included property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.EnumConstant:

=======================================================================
EnumConstant
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for enumConstant complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="enumConstant"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **EnumConstant** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.Field:

=======================================================================
Field
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for field complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="field"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="type" type="{}typeInfo" minOccurs="0"`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="constant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="scope" type="{}scope"`` ``attribute name="volatile" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="transient" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="static" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="final" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Field** ()


| **getType** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Gets the value of the type property.
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **setType** (value)
| Sets the value of the type property.
|          :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>` value


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getConstant** () → :ref:`String<java.lang.String>`
| Gets the value of the constant property.
|          returns :ref:`String<java.lang.String>`



| **setConstant** (value)
| Sets the value of the constant property.
|          :ref:`String<java.lang.String>` value


| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getScope** () → :ref:`String<java.lang.String>`
| Gets the value of the scope property.
|          returns :ref:`String<java.lang.String>`



| **setScope** (value)
| Sets the value of the scope property.
|          :ref:`String<java.lang.String>` value


| **isVolatile** () → boolean
| Gets the value of the volatile property.
|          returns boolean



| **setVolatile** (value)
| Sets the value of the volatile property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isTransient** () → boolean
| Gets the value of the transient property.
|          returns boolean



| **setTransient** (value)
| Sets the value of the transient property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isStatic** () → boolean
| Gets the value of the static property.
|          returns boolean



| **setStatic** (value)
| Sets the value of the static property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isFinal** () → boolean
| Gets the value of the final property.
|          returns boolean



| **setFinal** (value)
| Sets the value of the final property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.Interface:

=======================================================================
Interface
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for interface complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="interface"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="generic" type="{}typeParameter" maxOccurs="unbounded" minOccurs="0"`` ``element name="interface" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="method" type="{}method" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``element name="field" type="{}field" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="scope" type="{}scope"`` ``attribute name="included" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Interface** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getGeneric** () → :ref:`TypeParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeParameter>>`
| Gets the value of the generic property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the generic property. 
| For example, to add a new item, do as follows: `getGeneric().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeParameter`,
|          returns :ref:`TypeParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeParameter>>`



| **getInterface** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the interface property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the interface property. 
| For example, to add a new item, do as follows: `getInterface().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



| **getMethod** () → :ref:`Method><java.util.List<com.manticore.tools.xmldoclet.xjc.Method>>`
| Gets the value of the method property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the method property. 
| For example, to add a new item, do as follows: `getMethod().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Method`,
|          returns :ref:`Method><java.util.List<com.manticore.tools.xmldoclet.xjc.Method>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getField** () → :ref:`Field><java.util.List<com.manticore.tools.xmldoclet.xjc.Field>>`
| Gets the value of the field property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the field property. 
| For example, to add a new item, do as follows: `getField().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Field`,
|          returns :ref:`Field><java.util.List<com.manticore.tools.xmldoclet.xjc.Field>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getScope** () → :ref:`String<java.lang.String>`
| Gets the value of the scope property.
|          returns :ref:`String<java.lang.String>`



| **setScope** (value)
| Sets the value of the scope property.
|          :ref:`String<java.lang.String>` value


| **isIncluded** () → boolean
| Gets the value of the included property.
|          returns boolean



| **setIncluded** (value)
| Sets the value of the included property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.Method:

=======================================================================
Method
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for method complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="method"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="parameter" type="{}methodParameter" maxOccurs="unbounded" minOccurs="0"`` ``element name="return" type="{}typeInfo" minOccurs="0"`` ``element name="exception" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="signature" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="scope" type="{}scope"`` ``attribute name="abstract" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="final" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="included" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true"`` ``attribute name="native" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="synchronized" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="static" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``attribute name="varArgs" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Method** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getParameter** () → :ref:`MethodParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.MethodParameter>>`
| Gets the value of the parameter property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the parameter property. 
| For example, to add a new item, do as follows: `getParameter().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`MethodParameter`,
|          returns :ref:`MethodParameter><java.util.List<com.manticore.tools.xmldoclet.xjc.MethodParameter>>`



| **getReturn** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Gets the value of the return property.
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **setReturn** (value)
| Sets the value of the return property.
|          :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>` value


| **getException** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the exception property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the exception property. 
| For example, to add a new item, do as follows: `getException().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getSignature** () → :ref:`String<java.lang.String>`
| Gets the value of the signature property.
|          returns :ref:`String<java.lang.String>`



| **setSignature** (value)
| Sets the value of the signature property.
|          :ref:`String<java.lang.String>` value


| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getScope** () → :ref:`String<java.lang.String>`
| Gets the value of the scope property.
|          returns :ref:`String<java.lang.String>`



| **setScope** (value)
| Sets the value of the scope property.
|          :ref:`String<java.lang.String>` value


| **isAbstract** () → boolean
| Gets the value of the abstract property.
|          returns boolean



| **setAbstract** (value)
| Sets the value of the abstract property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isFinal** () → boolean
| Gets the value of the final property.
|          returns boolean



| **setFinal** (value)
| Sets the value of the final property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isIncluded** () → boolean
| Gets the value of the included property.
|          returns boolean



| **setIncluded** (value)
| Sets the value of the included property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isNative** () → boolean
| Gets the value of the native property.
|          returns boolean



| **setNative** (value)
| Sets the value of the native property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isSynchronized** () → boolean
| Gets the value of the synchronized property.
|          returns boolean



| **setSynchronized** (value)
| Sets the value of the synchronized property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isStatic** () → boolean
| Gets the value of the static property.
|          returns boolean



| **setStatic** (value)
| Sets the value of the static property.
|          :ref:`Boolean<java.lang.Boolean>` value


| **isVarArgs** () → boolean
| Gets the value of the varArgs property.
|          returns boolean



| **setVarArgs** (value)
| Sets the value of the varArgs property.
|          :ref:`Boolean<java.lang.Boolean>` value



..  _com.manticore.tools.xmldoclet.xjc.MethodParameter:

=======================================================================
MethodParameter
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for methodParameter complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="methodParameter"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="type" type="{}typeInfo" minOccurs="0"`` ``element name="annotation" type="{}annotationInstance" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **MethodParameter** ()


| **getType** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Gets the value of the type property.
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **setType** (value)
| Sets the value of the type property.
|          :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>` value


| **getAnnotation** () → :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`AnnotationInstance`,
|          returns :ref:`AnnotationInstance><java.util.List<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.ObjectFactory:

=======================================================================
ObjectFactory
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| This object contains factory methods for each  Java content interface and Java element interface  generated in the com.manticore.tools.xmldoclet.xjc package.  
| An ObjectFactory allows you to programmatically  construct new instances of the Java representation  for XML content. The Java representation of XML  content can consist of schema derived interfaces  and classes representing the binding of schema  type definitions, element declarations and model  groups.  Factory methods for each of these are  provided in this class.

| **ObjectFactory** ()
| Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.manticore.tools.xmldoclet.xjc


| **createRoot** () → :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>`
| Create an instance of ,`Root`
|          returns :ref:`Root<com.manticore.tools.xmldoclet.xjc.Root>`



| **createPackage** () → :ref:`Package<com.manticore.tools.xmldoclet.xjc.Package>`
| Create an instance of ,`Package`
|          returns :ref:`Package<com.manticore.tools.xmldoclet.xjc.Package>`



| **createAnnotation** () → :ref:`Annotation<com.manticore.tools.xmldoclet.xjc.Annotation>`
| Create an instance of ,`Annotation`
|          returns :ref:`Annotation<com.manticore.tools.xmldoclet.xjc.Annotation>`



| **createAnnotationElement** () → :ref:`AnnotationElement<com.manticore.tools.xmldoclet.xjc.AnnotationElement>`
| Create an instance of ,`AnnotationElement`
|          returns :ref:`AnnotationElement<com.manticore.tools.xmldoclet.xjc.AnnotationElement>`



| **createAnnotationInstance** () → :ref:`AnnotationInstance<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>`
| Create an instance of ,`AnnotationInstance`
|          returns :ref:`AnnotationInstance<com.manticore.tools.xmldoclet.xjc.AnnotationInstance>`



| **createAnnotationArgument** () → :ref:`AnnotationArgument<com.manticore.tools.xmldoclet.xjc.AnnotationArgument>`
| Create an instance of ,`AnnotationArgument`
|          returns :ref:`AnnotationArgument<com.manticore.tools.xmldoclet.xjc.AnnotationArgument>`



| **createEnum** () → :ref:`Enum<com.manticore.tools.xmldoclet.xjc.Enum>`
| Create an instance of ,`Enum`
|          returns :ref:`Enum<com.manticore.tools.xmldoclet.xjc.Enum>`



| **createEnumConstant** () → :ref:`EnumConstant<com.manticore.tools.xmldoclet.xjc.EnumConstant>`
| Create an instance of ,`EnumConstant`
|          returns :ref:`EnumConstant<com.manticore.tools.xmldoclet.xjc.EnumConstant>`



| **createInterface** () → :ref:`Interface<com.manticore.tools.xmldoclet.xjc.Interface>`
| Create an instance of ,`Interface`
|          returns :ref:`Interface<com.manticore.tools.xmldoclet.xjc.Interface>`



| **createClass** () → :ref:`Class<com.manticore.tools.xmldoclet.xjc.Class>`
| Create an instance of ,`Class`
|          returns :ref:`Class<com.manticore.tools.xmldoclet.xjc.Class>`



| **createConstructor** () → :ref:`Constructor<com.manticore.tools.xmldoclet.xjc.Constructor>`
| Create an instance of ,`Constructor`
|          returns :ref:`Constructor<com.manticore.tools.xmldoclet.xjc.Constructor>`



| **createMethod** () → :ref:`Method<com.manticore.tools.xmldoclet.xjc.Method>`
| Create an instance of ,`Method`
|          returns :ref:`Method<com.manticore.tools.xmldoclet.xjc.Method>`



| **createMethodParameter** () → :ref:`MethodParameter<com.manticore.tools.xmldoclet.xjc.MethodParameter>`
| Create an instance of ,`MethodParameter`
|          returns :ref:`MethodParameter<com.manticore.tools.xmldoclet.xjc.MethodParameter>`



| **createField** () → :ref:`Field<com.manticore.tools.xmldoclet.xjc.Field>`
| Create an instance of ,`Field`
|          returns :ref:`Field<com.manticore.tools.xmldoclet.xjc.Field>`



| **createTypeInfo** () → :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`
| Create an instance of ,`TypeInfo`
|          returns :ref:`TypeInfo<com.manticore.tools.xmldoclet.xjc.TypeInfo>`



| **createTypeParameter** () → :ref:`TypeParameter<com.manticore.tools.xmldoclet.xjc.TypeParameter>`
| Create an instance of ,`TypeParameter`
|          returns :ref:`TypeParameter<com.manticore.tools.xmldoclet.xjc.TypeParameter>`



| **createWildcard** () → :ref:`Wildcard<com.manticore.tools.xmldoclet.xjc.Wildcard>`
| Create an instance of ,`Wildcard`
|          returns :ref:`Wildcard<com.manticore.tools.xmldoclet.xjc.Wildcard>`



| **createTagInfo** () → :ref:`TagInfo<com.manticore.tools.xmldoclet.xjc.TagInfo>`
| Create an instance of ,`TagInfo`
|          returns :ref:`TagInfo<com.manticore.tools.xmldoclet.xjc.TagInfo>`




..  _com.manticore.tools.xmldoclet.xjc.Package:

=======================================================================
Package
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for package complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="package"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="comment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"`` ``element name="tag" type="{}tagInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="annotation" type="{}annotation" maxOccurs="unbounded" minOccurs="0"`` ``element name="enum" type="{}enum" maxOccurs="unbounded" minOccurs="0"`` ``element name="interface" type="{}interface" maxOccurs="unbounded" minOccurs="0"`` ``element name="class" type="{}class" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Package** ()


| **getComment** () → :ref:`String<java.lang.String>`
| Gets the value of the comment property.
|          returns :ref:`String<java.lang.String>`



| **setComment** (value)
| Sets the value of the comment property.
|          :ref:`String<java.lang.String>` value


| **getTag** () → :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`
| Gets the value of the tag property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the tag property. 
| For example, to add a new item, do as follows: `getTag().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TagInfo`,
|          returns :ref:`TagInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TagInfo>>`



| **getAnnotation** () → :ref:`Annotation><java.util.List<com.manticore.tools.xmldoclet.xjc.Annotation>>`
| Gets the value of the annotation property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the annotation property. 
| For example, to add a new item, do as follows: `getAnnotation().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Annotation`,
|          returns :ref:`Annotation><java.util.List<com.manticore.tools.xmldoclet.xjc.Annotation>>`



| **getEnum** () → :ref:`Enum><java.util.List<com.manticore.tools.xmldoclet.xjc.Enum>>`
| Gets the value of the enum property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the enum property. 
| For example, to add a new item, do as follows: `getEnum().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Enum`,
|          returns :ref:`Enum><java.util.List<com.manticore.tools.xmldoclet.xjc.Enum>>`



| **getInterface** () → :ref:`Interface><java.util.List<com.manticore.tools.xmldoclet.xjc.Interface>>`
| Gets the value of the interface property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the interface property. 
| For example, to add a new item, do as follows: `getInterface().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Interface`,
|          returns :ref:`Interface><java.util.List<com.manticore.tools.xmldoclet.xjc.Interface>>`



| **getClazz** () → :ref:`Class><java.util.List<com.manticore.tools.xmldoclet.xjc.Class>>`
| Gets the value of the clazz property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the clazz property. 
| For example, to add a new item, do as follows: `getClazz().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Class`,
|          returns :ref:`Class><java.util.List<com.manticore.tools.xmldoclet.xjc.Class>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.Root:

=======================================================================
Root
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for anonymous complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="package" type="{}package" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Root** ()


| **getPackage** () → :ref:`Package><java.util.List<com.manticore.tools.xmldoclet.xjc.Package>>`
| Gets the value of the package property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the package property. 
| For example, to add a new item, do as follows: `getPackage().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`Package`,
|          returns :ref:`Package><java.util.List<com.manticore.tools.xmldoclet.xjc.Package>>`




..  _com.manticore.tools.xmldoclet.xjc.TagInfo:

=======================================================================
TagInfo
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for tagInfo complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="tagInfo"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="text" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **TagInfo** ()


| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value


| **getText** () → :ref:`String<java.lang.String>`
| Gets the value of the text property.
|          returns :ref:`String<java.lang.String>`



| **setText** (value)
| Sets the value of the text property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.TypeInfo:

=======================================================================
TypeInfo
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for typeInfo complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="typeInfo"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="wildcard" type="{}wildcard" minOccurs="0"`` ``element name="generic" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="qualified" type="{http://www.w3.org/2001/XMLSchema}string"`` ``attribute name="dimension" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **TypeInfo** ()


| **getWildcard** () → :ref:`Wildcard<com.manticore.tools.xmldoclet.xjc.Wildcard>`
| Gets the value of the wildcard property.
|          returns :ref:`Wildcard<com.manticore.tools.xmldoclet.xjc.Wildcard>`



| **setWildcard** (value)
| Sets the value of the wildcard property.
|          :ref:`Wildcard<com.manticore.tools.xmldoclet.xjc.Wildcard>` value


| **getGeneric** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the generic property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the generic property. 
| For example, to add a new item, do as follows: `getGeneric().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



| **getQualified** () → :ref:`String<java.lang.String>`
| Gets the value of the qualified property.
|          returns :ref:`String<java.lang.String>`



| **setQualified** (value)
| Sets the value of the qualified property.
|          :ref:`String<java.lang.String>` value


| **getDimension** () → :ref:`String<java.lang.String>`
| Gets the value of the dimension property.
|          returns :ref:`String<java.lang.String>`



| **setDimension** (value)
| Sets the value of the dimension property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.TypeParameter:

=======================================================================
TypeParameter
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for typeParameter complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="typeParameter"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="bound" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string"`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **TypeParameter** ()


| **getBound** () → :ref:`String><java.util.List<java.lang.String>>`
| Gets the value of the bound property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the bound property. 
| For example, to add a new item, do as follows: `getBound().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`String`,
|          returns :ref:`String><java.util.List<java.lang.String>>`



| **getName** () → :ref:`String<java.lang.String>`
| Gets the value of the name property.
|          returns :ref:`String<java.lang.String>`



| **setName** (value)
| Sets the value of the name property.
|          :ref:`String<java.lang.String>` value



..  _com.manticore.tools.xmldoclet.xjc.Wildcard:

=======================================================================
Wildcard
=======================================================================

*extends:* :ref:`Object<java.lang.Object>` 

| 
| Java class for wildcard complex type. 
| The following schema fragment specifies the expected content contained within this class. `{@code ``complexType name="wildcard"`` ``complexContent`` ``restriction base="{http://www.w3.org/2001/XMLSchema}anyType"`` ``sequence`` ``element name="extendsBound" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``element name="superBound" type="{}typeInfo" maxOccurs="unbounded" minOccurs="0"`` ``/sequence`` ``/restriction`` ``/complexContent`` ``/complexType`` }`

| **Wildcard** ()


| **getExtendsBound** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the extendsBound property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the extendsBound property. 
| For example, to add a new item, do as follows: `getExtendsBound().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



| **getSuperBound** () → :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`
| Gets the value of the superBound property. 
| This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present inside the JAXB object. This is why there is not a ``set`` method for the superBound property. 
| For example, to add a new item, do as follows: `getSuperBound().add(newItem);` 
| Objects of the following type(s) are allowed in the list ,`TypeInfo`,
|          returns :ref:`TypeInfo><java.util.List<com.manticore.tools.xmldoclet.xjc.TypeInfo>>`



