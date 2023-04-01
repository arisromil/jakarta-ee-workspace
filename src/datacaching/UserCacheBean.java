

/*
using the Singleton annotation because it has one and only one instance in the application
context. This is the way we want a data cache to be – we don't want different data being
shared.

Also, used the @Startup annotation. This tells the server that this bean should
be executed once it is loaded and that the method annotated with @PostConstruct is used
for it.

ConcurrentLinkedQueue is a list that's built with one main purpose – to be accessed by
multiple processes in a thread-safe environment.

Annotated the get() method with LockType.READ, which tells the concurrency
manager that it can be accessed by multiple processes at once in a thread-safe way.
 */
@Singleton
@Startup
public class UserCacheBean {
    protected Queue<User> cache = null;
    @PersistenceContext
    private EntityManager em;
    public UserCacheBean() {
    }
    protected void loadCache() {
        List<User> list = em.createQuery("SELECT u FROM USER
                as u").getResultList();
                list.forEach((user) -> {
                    cache.add(user);
                });
    }
    @Lock(LockType.READ)
    public List<User> get() {
        return cache.stream().collect(Collectors.toList());
    }
    @PostConstruct
    protected void init() {
        cache = new ConcurrentLinkedQueue<>();
        loadCache();
    }
}
