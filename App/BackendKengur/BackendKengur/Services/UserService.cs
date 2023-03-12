using BackendKengur.Models;
using BackendKengur.Services.Interfaces;
using BackendKengur.DAL.Interfaces;

namespace BackendKengur.Services
{
    public class UserService : IUserService
    {
        private readonly IUserDAL userDAL;

        public UserService(IUserDAL _userDAL)
        {
            userDAL = _userDAL;
        }

        public MyResponse AddNewUser(User user)
        {
            var succeed=userDAL.AddNewUser(user);
            if (!succeed)
            {
                return new MyResponse
                {
                    Message = "Error while creating user",
                    StatusCode = StatusCodes.Status400BadRequest
                };
            }
            return new MyResponse
            {
                Message = "User successfully added",
                StatusCode = StatusCodes.Status200OK
            };
        }

        public User? GetUser(string email)
        {
            return userDAL.GetUser(email);
        }

        public Task<bool> UserAlreadyExists(string email)
        {
            throw new NotImplementedException();
        }
    }
}
