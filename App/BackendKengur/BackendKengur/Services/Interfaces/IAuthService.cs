using BackendKengur.DTOComponents;
using BackendKengur.Models;

namespace BackendKengur.Services.Interfaces
{
    public interface IAuthService
    {
        Task<MyResponse> Register(RegisterDTO registerDTO);
        Task<MyResponse> Login(LoginDTO loginDTO);
    }
}
