using BackendKengur.DAL;
using BackendKengur.DAL.Interfaces;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using BackendKengur.UI.Interfaces;
using BackendKengur.UI;
using Microsoft.Extensions.Options;
using MongoDB.Driver;
using BackendKengur.Services.Interfaces;
using BackendKengur.Services;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.IdentityModel.Tokens;
using System.Text;
using BackendKengur.JWTManager.Interfaces;
using BackendKengur.JWTManager;
using Microsoft.Extensions.FileProviders;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.

builder.Services.Configure<KengurDatabaseSettings>(
    builder.Configuration.GetSection(nameof(KengurDatabaseSettings)));

builder.Services.AddSingleton<IKengurDatabaseSettings>(s =>
    s.GetRequiredService<IOptions<KengurDatabaseSettings>>().Value);


builder.Services.AddSingleton<IMongoClient>(s =>
    new MongoClient(builder.Configuration.GetValue<string>("KengurDatabaseSettings:ConnectionString")));


// CORS Configuration 
builder.Services.AddCors(options =>
    options.AddDefaultPolicy(builder =>
    {
        builder.WithOrigins("https://localhost:7231")
        .AllowAnyHeader()
        .AllowAnyMethod();
    }
));


// Configure Authentication

builder.Services.AddAuthentication(item =>
{
    item.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
    item.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
})
    .AddJwtBearer(options => {
        options.RequireHttpsMetadata = true;
        options.SaveToken = true;
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuerSigningKey = true,
            IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration.GetSection("JWTSettings:Token").Value)),
            ValidateIssuer = false,
            ValidateAudience = false,
            ClockSkew = TimeSpan.Zero

        };
    });


builder.Services.AddControllers();
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(/*options => {
    options.AddSecurityDefinition("oauth2", new OpenApiSecurityScheme
    {
        In = ParameterLocation.Header,
        Name = "Authorization",
        Type = SecuritySchemeType.ApiKey
    });
    options.OperationFilter<SecurityRequirementsOperationFilter>();
}*/);


#region 'Interface - Class Dependency Injection'

#region 'DAL Dependencies'

builder.Services.AddScoped<ISchoolDAL, SchoolDAL>();
builder.Services.AddTransient<IUserDAL, UserDAL>();
builder.Services.AddTransient<IAssignmentDAL, AssignmentDAL>();
builder.Services.AddTransient<ILeaderboardDAL, LeaderboardDAL>();


#endregion

#region 'Services - BL Dependencies'

builder.Services.AddTransient<ISchoolService, SchoolService>();
builder.Services.AddTransient<IUserService, UserService>();
builder.Services.AddTransient<IAuthService, AuthService>();
builder.Services.AddTransient<IAssignmentService, AssignmentService>();
builder.Services.AddTransient<IFileService, FileService>();
builder.Services.AddTransient<ILeaderboardService, LeaderboardService>();

#endregion

#region 'UI - Dependencies'

builder.Services.AddTransient<ISchoolUI, SchoolUI>();
builder.Services.AddTransient<IUserUI, UserUI>();
builder.Services.AddTransient<IAssignmentUI, AssignmentUI>();
builder.Services.AddTransient<ILeaderboardUI, LeaderboardUI>();

#endregion

#region 'Managers'
builder.Services.AddTransient<IEncryptionManager, EncryptionManager>();
builder.Services.AddTransient<IJWTManagerRepository, JWTManagerRepository>();

#endregion

#endregion

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

//app.UseHttpsRedirection(); //mozda ovo obrisati

app.UseAuthorization();

app.UseCors();

app.MapControllers();


app.UseStaticFiles(new StaticFileOptions
{
    FileProvider = new PhysicalFileProvider(
           Path.Combine(builder.Environment.ContentRootPath, "Images")),
    RequestPath = "/Images"
});


app.Run();
