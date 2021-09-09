package overonix.overo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import overonix.overo.entity.User;
import overonix.overo.exception.NotAllowsCoordinateException;
import overonix.overo.repository.UserRepository;


import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getByAddress(String address) {
        logger.debug("It is getByAddress method");
        List<User> userList = new ArrayList<>();
        for (User user : userList) {
            if (user.getAddress().equals(address)) {
                return user;
            }
        }
        return null;
    }

    @CachePut(value = "users")
    @Override
    public List<User> getByCoordinate(double latitude, double longitude) throws NotAllowsCoordinateException {
        logger.debug("It is getByCoordinates method");
        if ((latitude > 90.0) || (latitude < 0) || (longitude < 0) || (longitude > 180)) {
            logger.debug("Exception. Latitude or longitude is wrong");
            throw new NotAllowsCoordinateException();
        }
        List<User> userList = new ArrayList<>();
        List<User> userListFill = new ArrayList<>();
        for (User user : userList) {
            if (latitude == user.getLatitude() && (longitude == user.getLongitude())) {
                userListFill.add(user);
            }
        }
        return userListFill;
    }

    @Override
    public User findByIdUser(long id) {
        logger.debug("It is findByIdUser method.");
        return userRepository.getById(id);
    }

    @Override
    public void saveUser(User user) {
        logger.debug("It is saveUser method");
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        logger.debug("It is updateUser method");
        saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        logger.debug("It is deleteUser method");
        userRepository.delete(user);
    }

}
