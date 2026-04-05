package repository;

public class AdminRepository {

    private static AdminRepository adminRepository;

    private AdminRepository() {
    }

    public static AdminRepository getInstance() {
        if (adminRepository == null) {
            adminRepository = new AdminRepository();
        }
        return adminRepository;
    }
}
