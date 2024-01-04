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
        private readonly ISchoolService schoolService;
        private readonly IEncryptionManager encryptionManager;
        private readonly IJWTManagerRepository JWTManager;


        public AuthService(IUserService userService,
                           ISchoolService schoolService,
                           IEncryptionManager encryptionManager, 
                           IJWTManagerRepository JWTManager)
        {
            this.userService = userService;
            this.schoolService = schoolService;
            this.encryptionManager = encryptionManager;
            this.JWTManager = JWTManager;
        }


        public async Task<MyResponse> Login(LoginDTO loginDTO)
        {
            var response = new MyResponse();
            var user = await userService.GetUser(loginDTO.Email);
            if (user == null)
            {
                response.StatusCode = StatusCodes.Status404NotFound;
                response.Message = "User doesn't exist!";
                return response;
            }

            var result = await encryptionManager.DecryptPassword(loginDTO.Password, user.Password!, user.PasswordKey!);

            if (result == false)
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
            var user = await userService.GetUser(registerDTO.Email);
            if(user!=null)
            {
                response.StatusCode = StatusCodes.Status400BadRequest;
                response.Message = "User already exists";
                return response;
            }

            var school = await schoolService.GetSchoolByNameAndCity(registerDTO.School);

            (passwordHash, passwordKey) = await encryptionManager.EncryptPassword(registerDTO.Password);
            User newUser = new User
            {
                FirstName = registerDTO.FirstName,
                LastName = registerDTO.LastName,
                Email = registerDTO.Email,
                Gender = registerDTO.Gender,
                Grade = registerDTO.Grade,
                School = new School { 
                    Id = school.Id,
                    Name = school.Name,
                    City = school.City},
                Class = registerDTO.Class,
                Password = passwordHash,
                PasswordKey = passwordKey
            };

            return await userService.AddNewUser(newUser);
            
        }
    }
}
