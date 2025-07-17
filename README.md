# ExecutableEvents

**ExecutableEvents** is a powerful Minecraft plugin that allows server administrators to customize and execute commands on virtually any server event. Create dynamic, interactive server experiences by triggering actions based on player activities, block interactions, entity behaviors, and much more.

## Features

- **Comprehensive Event System**: Handle 100+ different Minecraft events
- **Custom Command Execution**: Execute any command when an event occurs
- **Player-Based Events**: Block break/place, combat, movement, item interactions
- **Entity Events**: Mob spawning, deaths, breeding, transformations
- **World Events**: Weather changes, chunk loading, structure generation
- **Block Events**: Redstone activation, burning, growth, decay
- **Advanced Conditions**: Set complex requirements for event triggering
- **Multi-Language Support**: Available in multiple languages
- **Premium Features**: Enhanced cooldown systems for operators
- **Folia Support**: Compatible with Folia server software

## Requirements

- **Java**: 8 or higher
- **Minecraft Server**: Spigot/Paper 1.13+ (recommended: Paper 1.21+)
- **Dependencies**: SCore (soft dependency)

## Installation


1. Download the latest release from [Modrinth](https://modrinth.com/plugin/executableevents)
2. Place the `ExecutableEvents.jar` file in your server's `plugins` folder
3. Start/restart your server
4. Configure events in the `plugins/ExecutableEvents/events/` directory

## Quick Start

After installation, ExecutableEvents will create default configuration files:

- `config.yml` - Main plugin configuration
- `events/Default/test.yml` - Example event configuration

### Creating Your First Event

1. Go in-game
2. Type /ee editor
3. Configure the event trigger and actions

## Building from Source

### Prerequisites

- Java 8 or higher
- Maven 3.6+
- Git

### Build Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/ssomar/ExecutableEvents.git
   cd ExecutableEvents
   ```

2. **Build the project**:
   ```bash
   mvn clean package
   ```

3. **Locate the built JAR**:
   The compiled plugin will be in `target/ExecutableEvents-{version}.jar`

### Development Build

For development builds with SCore integration:
```bash
mvn clean package -P Ssomar
```

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/ee reload` | Reload plugin configuration | executableevents.admin |
| `/ee editor` | Open the event editor GUI | executableevents.admin |
| `/ee create <name>` | Create a new event | executableevents.admin |
| `/ee delete <name>` | Delete an event | executableevents.admin |
| `/ee show <name>` | Display event information | executableevents.admin |

## Configuration

### Event Configuration

Events are stored in YAML files within the `events/` directory. Each event can have:

- **Activator**: The trigger condition (e.g., PLAYER_BLOCK_BREAK)
- **Conditions**: Requirements that must be met
- **Actions**: Commands/actions to execute
- **Cooldowns**: Time restrictions between triggers
- **Permissions**: Required permissions for activation

## Supported Events

ExecutableEvents supports a wide variety of event types:

### Player Events
- Block break/place events
- Combat and damage events
- Movement and teleportation
- Item interactions
- Chat and command events
- And many more...

### Entity Events
- Spawn and death events
- Breeding and taming
- Combat interactions
- Transformations
- Portal usage

### World Events
- Weather changes
- Chunk loading/unloading
- Structure generation
- Day/night cycles

### Block Events
- Redstone activation
- Growth and decay
- Burning and explosions
- Player interactions

## Contributing

We welcome contributions from the community! Here's how you can help:

### Getting Started

1. **Fork the repository** on GitHub
2. **Create a feature branch**: `git checkout -b feature/your-feature-name`
3. **Make your changes** following our coding standards
4. **Test thoroughly** on different Minecraft versions
5. **Submit a pull request** with a clear description

### Development Guidelines

- Follow Java 8 coding standards
- Write clear, documented code
- Test with multiple Minecraft versions
- Ensure compatibility with Paper and Spigot
- Follow the existing project structure

### Code Style

- Use 4 spaces for indentation
- Follow camelCase naming conventions
- Add JavaDoc comments for public methods
- Keep methods focused and concise
- Use meaningful variable names

### Testing

Before submitting:
1. Test on Paper 1.21+ server
2. Verify compatibility with SCore
3. Test event triggering and execution
4. Check for memory leaks during long sessions

### Pull Request Process

1. Update documentation if needed
2. Ensure all tests pass
3. Update version numbers appropriately
4. Provide a clear description of changes
5. Link any related issues

## Support

- **Discord**: [Join our Discord server](https://discord.com/invite/TRmSwJaYNv)
- **Documentation**: [Official Wiki](https://docs.ssomar.com/executableevents/information-ee)
- **Issues**: Report bugs on our [GitHub Issues page](https://github.com/ssomar/ExecutableEvents/issues)

## License

This project is licensed under a Custom License that allows personal use while prohibiting paid redistribution. See the [LICENSE](LICENSE) file for complete terms.

### License Summary

**Permitted:**
- Personal, educational, and non-commercial use
- Source code access and modification for personal learning
- Non-commercial distribution with license included

**Prohibited:**
- Commercial use without permission
- Paid distribution or redistribution
- Commercial hosting as a paid service

For commercial licensing inquiries, please contact: **Marceau LEYMONERIE**

## Acknowledgments

- **Author**: Marceau LEYMONERIE

- **Contributors**: All community members who have contributed to the project
- **Special Thanks**: The Minecraft modding community for their support and feedback

## Changelog

For detailed version history and changes, please see our [Modrinth releases](https://modrinth.com/plugin/executableevents/versions).

---

**Made with ❤️ by the ExecutableEvents team**
