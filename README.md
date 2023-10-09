# Description
- Use the api-contracts that have been deployed to the Sonatype Nexus Repository to generate sources.

- Deploy api-contracts to a remote repository as a dependency, then add this dependency to the project. Unpack the dependency during the validate phase, generate source code to a specified directory, and utilize those classes.

# How to run
### [1. Clone repository](https://github.com/nhanngh/api-docs)
### 2. Run project
`mvn spring-boot:run`

# Structure pom.xml 
api-contract dependency is installed in **step 1** with **groupId** and **artifactId** of api-docs project
```
<dependency>
  <groupId>com.demo.bkit</groupId>
  <artifactId>api-docs</artifactId>
  <type>rar</type>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

This plugin to unpack dependencies is added above, and it is unpacked in the _${basedir}/target/api-contracts_ directory.
```
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-dependency-plugin</artifactId>
      <executions>
        <execution>
          <id>unpack</id>
          <goals>
            <goal>unpack-dependencies</goal>
          </goals>
          <phase>validate</phase>
          <configuration>
            <includeGroupIds>com.demo.bkit</includeGroupIds>
            <includeArtifactIds>api-docs</includeArtifactIds>
            <outputDirectory>${basedir}/target/api-contracts</outputDirectory>
          </configuration>
        </execution>
      </executions>
    </plugin>
    
    ...

  </plugins>
</build>
```

This plugin to generate sources from api-contracts unpacked.
```
<plugin>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-generator-maven-plugin</artifactId>
  <version>6.6.0</version>
  <executions>

    <execution>
      <id>generate-product-service</id>
      <goals>
        <goal>generate</goal>
      </goals>
      <configuration>
        <inputSpec>
          ${project.basedir}/target/api-contracts/product-service.yaml
        </inputSpec>
        <generatorName>spring</generatorName>
        <apiPackage>com.example.demoloadapidocs</apiPackage>
        <modelPackage>com.example.demoloadapidocs.model</modelPackage>
        <generateSupportingFiles>false</generateSupportingFiles>
        <configOptions>
          <interfaceOnly>true</interfaceOnly>
          <delegatePattern>true</delegatePattern>
          <useSpringBoot3>true</useSpringBoot3>
          <!--								<useJakartaEe>true</useJakartaEe>-->
          <hideGenerationTimestamp>true</hideGenerationTimestamp>
          <skipDefaultInterface>true</skipDefaultInterface>
          <dateLibrary>java8</dateLibrary>
        </configOptions>
        <typeMappings>
          <typeMapping>string+date-time=ZonedDateTime</typeMapping>
          <typeMapping>object+RateLimitCode=RateLimitCode</typeMapping>
        </typeMappings>
        <schemaMappings>
          <schemaMapping>ZonedDateTime=java.time.ZonedDateTime</schemaMapping>
          <schemaMapping>RateLimitCode=com.example.demoloadapidocs.RateLimitCode</schemaMapping>
        </schemaMappings>
      </configuration>
    </execution>

    <execution>
      <id>generate-file-storage-service</id>
      <goals>
        <goal>generate</goal>
      </goals>
      <configuration>
        <inputSpec>
          ${project.basedir}/target/api-contracts/file-storage-service.yaml
        </inputSpec>
        <globalProperties>
          <skipFormModel>false</skipFormModel>
        </globalProperties>
        <generatorName>spring</generatorName>
        <library>spring-cloud</library>
        <apiPackage>com.example.demoloadapidocs.module.fileservice.api</apiPackage>
        <modelPackage>com.example.demoloadapidocs.module.fileservice.model</modelPackage>
        <configOptions>
          <useSpringBoot3>true</useSpringBoot3>
          <hideGenerationTimestamp>true</hideGenerationTimestamp>
          <dateLibrary>java8</dateLibrary>
          <useTags>true</useTags>
        </configOptions>
        <typeMappings>
          <typeMapping>string+date-time=ZonedDateTime</typeMapping>
        </typeMappings>
        <schemaMappings>
          <schemaMapping>ZonedDateTime=java.time.ZonedDateTime</schemaMapping>
        </schemaMappings>
      </configuration>
    </execution>

  </executions>
</plugin>
```
