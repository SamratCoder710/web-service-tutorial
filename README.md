Web Service
============

- A Web Service is machine to machine (or application to application) interaction.
- It should be interoperable (not platform independent).
- It should allow communication over a network.

Message Exchange format generally is XML or JSON

Two Basic Categories of Web Services:-
1. SOAP (use SOAP-XML as request -response format)
2. REST/ RESTFUL / REST API Web Services (Build on top of HTTP. Use HTTP methods for executing operations)


SOAP XML Sample Response Output:
--------------------------------
<?xml version="1.0"?>
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Header/>
  <SOAP-ENV:Body>
    <ns2:getCountryResponse xmlns:ns2="http://spring.io/guides/gs-producing-web-service">
      <ns2:country>
        <ns2:name>Spain</ns2:name>
        <ns2:population>46704314</ns2:population>
        <ns2:capital>Madrid</ns2:capital>
        <ns2:currency>EUR</ns2:currency>
      </ns2:country>
    </ns2:getCountryResponse>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
---------------------------------




