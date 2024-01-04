using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.UI.Interfaces
{
    public interface IUserUI
    {
        Task<UserDTO> GetUser(string email);
        UserDTO CreateUserDTO(User user);
    }
}
