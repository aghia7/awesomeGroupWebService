import data.PostgresConnection;
import data.interfaces.DBConnection;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import repositories.UserRepository;
import repositories.interfaces.IUserRepository;

public class MyApplicationBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(PostgresConnection.class).to(DBConnection.class);
        bind(UserRepository.class).to(IUserRepository.class);
    }
}
