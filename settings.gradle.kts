include("src:main:test")
findProject(":src:main:test")?.name = "test"
include("src:test:java")
findProject(":src:test:java")?.name = "java"
include("src:test:java:task")
findProject(":src:test:java:task")?.name = "task"
include("src:test")
findProject(":src:test")?.name = "test"
include("src:test")
findProject(":src:test")?.name = "test"
include("src:test:java:task")
findProject(":src:test:java:task")?.name = "task"
