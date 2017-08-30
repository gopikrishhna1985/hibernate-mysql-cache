# hibernate-mysql-cache
A Simple Hibernate MySQL example explaining different Caching mechanisms offered in Hibernate

Hibernate Caching 

First level Cache (Session Object) enabled by default… available only for the session… retrieved from DB and store in first level cache, 
next db hit not done… but loaded entity can be removed by evict().. After that again call to db is made... 
To clear cache entirely use clear() 

Second level Cache(Session Factory)… enable it explicitly… available to entire application… first checks in first level cache… 
if not available in first level cache then looks into second level cache… if cache entity present in second level cache… 
it will store it into first level cache and then loads it.. 

This way next time app will get the info from first level cache itself… if not present in both caches, 
db query is executed and data is stored in both caches before returning… 

Caching Strategies offered -> READ_ONLY, READ_WRITE, NON RESTRICTED READ WRITE, TRANSACTIONAL 

If user updates db directly then cache will not reflect it… it's better to use the session api so that the cache is always updated 

HibernateUtil.getSessionFactory().getStatistics().getEntityFetchCount()  - gets the first level cache count 

HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount() - gets the second level cache count 

Various Second Level Caches available are,

EhCache, 

OSCache, 

Jboss related Cache 

Query Cache - If you use Query to fetch data, you can cache those results also using query cache. 
In order to achieve this Query cache has to be enabled in hibernate configuration file and the query.cacheable should be set to true in java side also

