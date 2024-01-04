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

        public async Task<UserDTO> GetUser(string email)
        {
           var user = await userService.GetUser(email);
           return CreateUserDTO(user);

        }

        public UserDTO CreateUserDTO(User user)
        {
            if (user == null)
                return null;

            return new UserDTO {
            
                FirstName = user.FirstName,
                LastName = user.LastName,
                School = user.School,
                Class = user.Class,
                SumPoints = user.SumPoints,
                TestNumber = user.TestNumber
            };
        }
    }
}
