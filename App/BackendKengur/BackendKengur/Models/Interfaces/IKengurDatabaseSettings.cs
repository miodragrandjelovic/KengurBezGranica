namespace BackendKengur.Models.Interfaces
{
    public interface IKengurDatabaseSettings
    {
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
        string TaskCollectionName { get; set; }
        string ResultCollectionName { get; set; }
        string SchoolCollectionName { get; set; }
        string UserCollectionName { get; set; }
    }
}
