package at.accounting_otter;

import at.accounting_otter.entity.User;

import javax.inject.Inject;


public class UserServiceImpl implements UserService {

    @Inject
    DatabaseAdapter databaseAdapter;

    @Override
    public User createUser(User user) {
        if (doesUsernameAlreadyExist(user.getUsername())) {
            throw new RuntimeException("Username does already exist.");
        } else {
            return databaseAdapter.createUser(user);
        }
    }

    @Override
    public User getUser(int userId) {
        return databaseAdapter.getUser(userId);
    }

    @Override
    public User changeUsername(int userId, String newUsername) throws RuntimeException, ObjectNotFoundException {
        if (databaseAdapter.getUser(userId) == null) {
            throw new ObjectNotFoundException("User with id " + userId + " not found.");
        }

        if (doesUsernameAlreadyExist(newUsername)) {
            throw new RuntimeException("Username does already exist.");
        } else {
            User user = databaseAdapter.getUser(userId);
            user.setUsername(newUsername);
            user = databaseAdapter.updateUser(user);
            return user;
        }
    }

    private boolean doesUsernameAlreadyExist(String username) {
        User user = databaseAdapter.findUserByUsername(username);
        return user != null;
    }

    @Override
    public void removeUser(int userId) throws ObjectNotFoundException {
        if (databaseAdapter.getUser(userId) == null) {
            throw new ObjectNotFoundException("User with id " + userId + " not found.");
        } else {
            databaseAdapter.removeUser();
        }
    }

}