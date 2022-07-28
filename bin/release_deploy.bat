cd ..
mvn -X  clean deploy -pl ichao-lottery-base,ichao-lottery-api,ichao-lottery-api-http -am "-Dmaven.test.skip=true" "-Ddeploy-version.type=RELEASE"
