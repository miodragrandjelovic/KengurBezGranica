using BackendKengur.Models;

namespace BackendKengur.DAL.Interfaces
{
    public interface IUserDAL
    {
        Task<bool> AddNewUser(User user);
        Task<User> GetUser(string email);
        Task<bool> UpdateUser(string email, double points);
    }
}
