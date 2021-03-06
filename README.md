# MongoDB + Hibernate OGM quick start using Oracle Application Container Cloud

Check [the blog](https://community.oracle.com/community/cloud_computing/oracle-cloud-developer-solutions/blog/2017/06/29/quickstart-mongodb-with-oracle-application-container-oracle-developer-cloud) for details

## Build

- `git clone https://github.com/abhirockzz/accs-mongodb.git`
- `mvn clean install` - creates `acc-mongodb-dcs-dist.zip` in `target` directory

## Deploy

- Use Developer Cloud - read [the blog](https://community.oracle.com/community/cloud_computing/oracle-cloud-developer-solutions/blog/2017/06/29/quickstart-mongodb-with-oracle-application-container-oracle-developer-cloud#jive_content_id_Setup)

*Make sure you update [deployment.json](https://github.com/abhirockzz/accs-mongodb/blob/master/deployment.json#L4) with the correct MongoDB co-ordinates before deploying using the below mentioned options*

- Use Application Container Cloud [console](http://docs.oracle.com/en/cloud/paas/app-container-cloud/csjse/exploring-application-deployments-page.html#GUID-5E4472B1-F5C6-4556-908C-D76C4C14FC60)
- Use Application Container Cloud [REST APIs](http://docs.oracle.com/en/cloud/paas/app-container-cloud/apcsr/op-paas-service-apaas-api-v1.1-apps-%7BidentityDomainId%7D-post.html)
- Use Application Container Cloud [PSM APIs](https://docs.oracle.com/en/cloud/paas/java-cloud/pscli/accs-push.html)
