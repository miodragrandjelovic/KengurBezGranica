using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.Services.Interfaces;
using BackendKengur.UI.Interfaces;

namespace BackendKengur.UI
{
    public class UserUI : IUserUI
    {
        private readonly IUserService userService;
        public UserUI(IUserService userService)
        {
            this.userService = userService;
        }

        public UserDTO? GetUser(string email)
        {
           var user = userService.GetUser(email);
            if(user == null)
                return null;
            return CreateUserDTO(user);

        }

        public UserDTO CreateUserDTO(User user)
        {
            var userDTO = new UserDTO {
            
                FirstName = user.FirstName,
                LastName = user.LastName,
                School = user.School,
                Class = user.Class,
                SumPoints = user.SumPoints,
                TestNumber = user.TestNumber
            };

            return userDTO;
        }
    }
}
