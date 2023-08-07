using MongoDB.Bson.Serialization.Attributes;
using MongoDB.Driver;

namespace BackendKengur.DTOComponents
{
    public class RegisterDTO
    {
        public string FirstName { get; set; } = string.Empty;
        public string LastName { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
        public short Gender { get; set; }
        public short Grade { get; set; }
        public string School { get; set; } = string.Empty;
        public short Class { get; set; }
        public string Password { get; set; } = string.Empty;
    }
}
