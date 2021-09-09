package overonix.overo.service;


import overonix.overo.entity.User;
import overonix.overo.exception.NotAllowsCoordinateException;

import java.util.List;

public interface UserService  {
    User getByAddress(String address);

    List<User> getByCoordinate(double latitude, double longitude) throws NotAllowsCoordinateException;

    User findByIdUser(long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
