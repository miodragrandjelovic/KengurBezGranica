using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface IUserService
    {
        Task<MyResponse> AddNewUser(User user);
        Task<bool> UserAlreadyExists(string email);
        Task<User> GetUser(string email);
    }
}
