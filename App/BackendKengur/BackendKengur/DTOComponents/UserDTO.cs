using BackendKengur.Models;

namespace BackendKengur.DTOComponents
{
    public class UserDTO
    {
        public string FirstName { get; set; } = string.Empty;
        public string LastName { get; set; } = string.Empty;
        public School? School { get; set; }
        public short Class { get; set; }
        public int TestNumber { get; set; } = 0;
        public float SumPoints { get; set; } = 0;

    }
}
