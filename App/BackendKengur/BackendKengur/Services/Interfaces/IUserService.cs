using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface IUserService
    {
        MyResponse AddNewUser(User user);
        Task<bool> UserAlreadyExists(string email);
        User? GetUser(string email);
    }
}
