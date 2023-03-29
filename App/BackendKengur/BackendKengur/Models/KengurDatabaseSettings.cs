using BackendKengur.Models.Interfaces;

namespace BackendKengur.Models
{
    public class KengurDatabaseSettings : IKengurDatabaseSettings
    {
        public string ConnectionString { get; set; } =string.Empty;
        public string DatabaseName { get; set; } = string.Empty;
        public string AssignmentCollectionName { get; set; } = string.Empty;
        public string ResultCollectionName { get; set; } = string.Empty;
        public string SchoolCollectionName { get; set; } = string.Empty;
        public string UserCollectionName { get; set; } = string.Empty;
    }
}
