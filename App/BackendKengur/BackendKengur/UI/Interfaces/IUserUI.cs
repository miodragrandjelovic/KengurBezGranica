using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.UI.Interfaces
{
    public interface IUserUI
    {
        UserDTO GetUser(string email);
        UserDTO CreateUserDTO(User user);
    }
}
