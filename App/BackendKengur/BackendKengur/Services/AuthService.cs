using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using BackendKengur.Services.Interfaces;
using BackendKengur.JWTManager.Interfaces;
using MongoDB.Driver;

namespace BackendKengur.Services
{
    public class AuthService : IAuthService
    {
        private readonly IUserService userService;
        private readonly IEncryptionManager encryptionManager;
        private readonly IJWTManagerRepository JWTManager;


        public AuthService(IUserService userService,
                           IEncryptionManager encryptionManager, 
                           IJWTManagerRepository JWTManager)
        {
            this.userService = userService;
            this.encryptionManager = encryptionManager;
            this.JWTManager = JWTManager;
        }


        public async Task<MyResponse> Login(LoginDTO loginDTO)
        {
            var response = new MyResponse();
            var user = userService.GetUser(loginDTO.Email);
            if (user == null)
            {
                response.StatusCode = StatusCodes.Status404NotFound;
                response.Message = "User doesn't exist!";
                return response;
            }

            if (!encryptionManager.DecryptPassword(loginDTO.Password, user.Password!, user.PasswordKey!))
            {
                response.StatusCode = StatusCodes.Status403Forbidden;
                response.Message = "Wrong password!";
                return response;
            }


            var tokenString = JWTManager.GenerateToken(user);
            response.StatusCode = StatusCodes.Status200OK;
            response.Message = tokenString;
            return response;
        }

        public async Task<MyResponse> Register(RegisterDTO registerDTO)
        {
            var response = new MyResponse();
            byte[] passwordHash, passwordKey;
            var user = userService.GetUser(registerDTO.Email);
            if(user!=null)
            {
                response.StatusCode = StatusCodes.Status400BadRequest;
                response.Message = "User already exists";
                return response;
            }


            encryptionManager.EncryptPassword(registerDTO.Password, out passwordHash, out passwordKey);
            User newUser = new User
            {
                FirstName = registerDTO.FirstName,
                LastName = registerDTO.LastName,
                Email = registerDTO.Email,
                School = new MongoDBRef(Constants.Constants.SCHOOL, registerDTO.School),
                Class = registerDTO.Class,
                Password = passwordHash,
                PasswordKey = passwordKey
            };

            return userService.AddNewUser(newUser);
            
        }
    }
}
