package br.com.pi.atostech.adapters.out.storage.user;

import br.com.pi.atostech.aplication.domain.UserDomain;

public interface UserAdapterOutInterface {

    UserDomain login(UserDomain userDomain);
    boolean create(UserDomain userDomain);
    boolean update(UserDomain userDomain);
    boolean delete(String email);
    UserDomain getUser(String email);
}
