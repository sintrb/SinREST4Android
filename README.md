SinREST4Android
===============

A REST client library for Android development, but in fact it can be used on all Java platform, such as J2ME, J2SE, J2EE and so on.

It't very easy to use it, in most time you can use it as follow:

1. Create a WebResource witch is Implemented by REST Server Protocol.

> WebResource resource = new WebResource("http://sinpy.sinaapp.com/rest/book/");

2. Then use this resource to create a ResourceAccess.

> ResourceAccess access = resource.get();	// A GET access, maybe it will get all resource

> ResourceAccess access = resource.get("1");	// A GET access, it will get the resource witch id==1 (maybe other identity equal "1")

> ResourceAccess access = resource.post(); // A POST access, use it to add a resource

> ResourceAccess access = resource.put(); // A PUT access, use it to modify resource

> ResourceAccess access = resource.delete("1"); // A DELETE access, use it to delete resource, the mean of "1" is like before.

3. After create ResourceAccess, you can set the access's attribute, such as encode, type, accept type and so on.

> access.accept(MediaType.APPLICATION_JSON); // Set accept type is JSON string

4. Every is ready, now use read() to execute the ResourceAccess, when read() you can submit some data to the REST Server.

> access.read();	// Simple read

> access.read(String);	// Submit a String and read the response

> access.read(Map<String, String>);	// Submit a Data Form and read the response



