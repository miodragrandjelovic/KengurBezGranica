using BackendKengur.JWTManager.Interfaces;
using BackendKengur.Models;
using System.Security.Claims;
using System.IdentityModel.Tokens.Jwt;
using Microsoft.IdentityModel.Tokens;

namespace BackendKengur.JWTManager
{
    public class JWTManagerRepository : IJWTManagerRepository
    {
        private readonly IConfiguration configuration;

        public JWTManagerRepository(IConfiguration configuration)
        {
            this.configuration = configuration;
        }



        public string GenerateToken(User user)
        {
            List<Claim> claims = new List<Claim>
            {
                new Claim(Constants.Constants.FIRST_NAME, user.FirstName),
                new Claim(Constants.Constants.LAST_NAME, user.LastName),
                new Claim(ClaimTypes.Email, user.Email)
            };

            var key = new SymmetricSecurityKey(System.Text.Encoding.UTF8.GetBytes(configuration.GetSection("JWTSettings:Token").Value));

            var cred = new SigningCredentials(key, SecurityAlgorithms.HmacSha512);

            var token = new JwtSecurityToken(
                claims: claims,
                expires: DateTime.Now.AddDays(7),
                signingCredentials: cred
            );

            var jwt = new JwtSecurityTokenHandler().WriteToken(token);


            return jwt;
        }

        public string? ReadToken(string token)
        {
            throw new NotImplementedException();
        }
    }
}
