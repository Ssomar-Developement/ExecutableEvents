package com.ssomar.executableevents.executableevents.activators;

import com.ssomar.score.sobject.sactivator.OptionGlobal;
import com.ssomar.score.sobject.sactivator.SOption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Option implements SOption, Serializable {

    BLOCK_DRY("BLOCK_DRY"),
    ITEMSADDER_PLAYER_BLOCK_BREAK("ITEMSADDER_PLAYER_BLOCK_BREAK"),
    CROP_GROW("CROP_GROW"),
    PLAYER_ACTIVE_FLY("PLAYER_ACTIVE_FLY"),
    PLAYER_ACTIVE_SNEAK("PLAYER_ACTIVE_SNEAK"),
    PLAYER_ACTIVE_SPRINT("PLAYER_ACTIVE_SPRINT"),
    PLAYER_ALL_CLICK("PLAYER_ALL_CLICK"),
    PLAYER_BED_ENTER("PLAYER_BED_ENTER"),
    PLAYER_BED_LEAVE("PLAYER_BED_LEAVE"),
    PLAYER_BEFORE_DEATH("PLAYER_BEFORE_DEATH"),
    PLAYER_BLOCK_BREAK("PLAYER_BLOCK_BREAK"),
    PLAYER_BLOCK_PLACE("PLAYER_BLOCK_PLACE"),
    PLAYER_CHANGE_WORLD("PLAYER_CHANGE_WORLD"),
    PLAYER_CLICK_ON_ENTITY("PLAYER_CLICK_ON_ENTITY"),
    PLAYER_CLICK_ON_PLAYER("PLAYER_CLICK_ON_PLAYER"),
    PLAYER_CONNECTION("PLAYER_CONNECTION"),
    PLAYER_CONSUME("PLAYER_CONSUME"),
    PLAYER_CUSTOM_LAUNCH("PLAYER_CUSTOM_LAUNCH"),
    PLAYER_DEATH("PLAYER_DEATH"),
    PLAYER_DISCONNECTION("PLAYER_DISCONNECTION"),
    PLAYER_DISABLE_SNEAK("PLAYER_DISABLE_SNEAK"),
    PLAYER_DISABLE_SPRINT("PLAYER_DISABLE_SPRINT"),
    PLAYER_DISMOUNT("PLAYER_DISMOUNT"),
    PLAYER_FOOD_CHANGE("PLAYER_FOOD_CHANGE"),
    PLAYER_MOUNT("PLAYER_MOUNT"),
    PLAYER_DROP_ITEM("PLAYER_DROP_ITEM"),
    PLAYER_EDIT_BOOK("PLAYER_EDIT_BOOK"),
    PLAYER_ENTER_IN_HIS_LAND("PLAYER_ENTER_IN_HIS_LAND"),
    PLAYER_ENTER_IN_HIS_PLOT("PLAYER_ENTER_IN_HIS_PLOT"),
    PLAYER_FERTILIZE_BLOCK("PLAYER_FERTILIZE_BLOCK"), /* NOT COMPATIBLE 1.12 */
    PLAYER_FILL_BUCKET("PLAYER_FILL_BUCKET"),
    PLAYER_FIRST_CONNECTION("PLAYER_FIRST_CONNECTION"),
    PLAYER_FISH_BLOCK("PLAYER_FISH_BLOCK"), /* NOT COMPATIBLE 1.12 */
    PLAYER_FISH_ENTITY("PLAYER_FISH_ENTITY"),
    PLAYER_FISH_FISH("PLAYER_FISH_FISH"),
    PLAYER_FISH_NOTHING("PLAYER_FISH_NOTHING"),
    PLAYER_FISH_PLAYER("PLAYER_FISH_PLAYER"),
    PLAYER_HIT_ENTITY("PLAYER_HIT_ENTITY"),
    PLAYER_HIT_PLAYER("PLAYER_HIT_PLAYER"),
    PLAYER_ITEM_BREAK("PLAYER_ITEM_BREAK"),
    PLAYER_JUMP("PLAYER_JUMP"),
    PLAYER_KILL_ENTITY("PLAYER_KILL_ENTITY"),
    PLAYER_KILL_PLAYER("PLAYER_KILL_PLAYER"),
    PLAYER_LEAVE_HIS_LAND("PLAYER_LEAVE_HIS_LAND"),
    PLAYER_LEAVE_HIS_PLOT("PLAYER_LEAVE_HIS_PLOT"),
    PLAYER_LEFT_CLICK("PLAYER_LEFT_CLICK"),
    PLAYER_WALK("PLAYER_WALK"),
    PLAYER_WRITE_COMMAND("PLAYER_WRITE_COMMAND"),
    PLAYER_RECEIVE_HIT_BY_ENTITY("PLAYER_RECEIVE_HIT_BY_ENTITY"),
    PLAYER_RECEIVE_HIT_BY_PLAYER("PLAYER_RECEIVE_HIT_BY_PLAYER"),
    PLAYER_RECEIVE_HIT_GLOBAL("PLAYER_RECEIVE_HIT_GLOBAL"),
    PLAYER_RESPAWN("PLAYER_RESPAWN"),
    PLAYER_RIGHT_CLICK("PLAYER_RIGHT_CLICK"),
    PLAYER_SHEAR_ENTITY("PLAYER_SHEAR_ENTITY"),
    PLAYER_TARGETED_BY_AN_ENTITY("PLAYER_TARGETED_BY_AN_ENTITY"),
    PLAYER_TRAMPLE_CROP("PLAYER_TRAMPLE_CROP"),

    PLAYER_TELEPORT("PLAYER_TELEPORT"),

    PLAYER_PARTICIPATE_KILL_ENTITY("PLAYER_PARTICIPATE_KILL_ENTITY"),
    PLAYER_PARTICIPATE_KILL_PLAYER("PLAYER_PARTICIPATE_KILL_PLAYER"),

    PLAYER_PICKUP_ITEM("PLAYER_PICKUP_ITEM"),
    PLAYER_PROJECTILE_HIT_BLOCK("PLAYER_PROJECTILE_HIT_BLOCK"),
    PLAYER_PROJECTILE_HIT_ENTITY("PLAYER_PROJECTILE_HIT_ENTITY"),
    PLAYER_PROJECTILE_HIT_PLAYER("PLAYER_PROJECTILE_HIT_PLAYER"),

    ENTITY_PARTICIPATE_KILL_ENTITY("ENTITY_PARTICIPATE_KILL_ENTITY"),
    ENTITY_PARTICIPATE_KILL_PLAYER("ENTITY_PARTICIPATE_KILL_PLAYER"),
    ENTITY_PROJECTILE_HIT_BLOCK("ENTITY_PROJECTILE_HIT_BLOCK"),
    ENTITY_PROJECTILE_HIT_ENTITY("ENTITY_PROJECTILE_HIT_ENTITY"),
    ENTITY_PROJECTILE_HIT_PLAYER("ENTITY_PROJECTILE_HIT_PLAYER"),

    ENTITY_SPAWN("ENTITY_SPAWN"),
    ENTITY_BREAK_DOOR("ENTITY_BREAK_DOOR"),
    ENTITY_BREED("ENTITY_BREED"),
    ENTITY_CHANGE_BLOCK("ENTITY_CHANGE_BLOCK"),
    ENTITY_COMBUST_BY_BLOCK("ENTITY_COMBUST_BY_BLOCK"),
    ENTITY_COMBUST_BY_ENTITY("ENTITY_COMBUST_BY_ENTITY"),
    ENTITY_DAMAGE_BY_PLAYER("ENTITY_DAMAGE_BY_PLAYER"),
    ENTITY_DAMAGE_BY_ENTITY("ENTITY_DAMAGE_BY_ENTITY"),
    ENTITY_DAMAGE_BY_BLOCK("ENTITY_DAMAGE_BY_BLOCK"),
    ENTITY_DEATH("ENTITY_DEATH"),
    ENTITY_DROP_ITEM("ENTITY_DROP_ITEM"),
    ENTITY_ENTER_BLOCK("ENTITY_ENTER_BLOCK"),
    ENTITY_ENTER_LOVE_MODE("ENTITY_ENTER_LOVE_MODE"),
    ENTITY_EXPLODE("ENTITY_EXPLODE"),
    ENTITY_PICKUP_ITEM("ENTITY_PICKUP_ITEM"),
    ENTITY_PORTAL_ENTER("ENTITY_PORTAL_ENTER"),
    ENTITY_PORTAL_EXIT("ENTITY_PORTAL_EXIT"),
    ENTITY_REGAIN_HEALTH("ENTITY_REGAIN_HEALTH"),
    ENTITY_RESURRECT("ENTITY_RESURRECT"),
    ENTITY_TAME_BY_PLAYER("ENTITY_TAME_BY_PLAYER"),
    ENTITY_TAME_BY_ENTITY("ENTITY_TAME_BY_ENTITY"),
    ENTITY_TARGET_PLAYER("ENTITY_TARGET_PLAYER"),
    ENTITY_TARGET_ENTITY("ENTITY_TARGET_ENTITY"),
    ENTITY_TELEPORT("ENTITY_TELEPORT"),
    ENTITY_TRANSFORM("ENTITY_TRANSFORM"),

    ENDERDRAGON_CHANGE_PHASE("ENDERDRAGON_CHANGE_PHASE");


    private String[] names;

    Option(String... names) {
        this.names = names;
    }

    public static List<SOption> getPremiumOptionSt() {
        List<SOption> result = new ArrayList<>();
        for (Option option : values()) {
            if (getOptionWithPlayerSt().contains(option) || option.isLoopOption()) continue;
            else result.add(option);
        }
        return result;
    }

    public static String getPremiumOptionAsString() {
        StringBuilder sb = new StringBuilder("");
        for (SOption option : getPremiumOptionSt()) {
            sb.append(option.toString());
            sb.append(" ");
        }
        sb.substring(0, sb.length() - 1);
        return sb.toString();
    }

    public static List<Option> getOptionWithDetailedDamageCauses() {
        List<Option> result = new ArrayList<>();
        result.add(Option.PLAYER_RECEIVE_HIT_BY_ENTITY);
        result.add(Option.PLAYER_RECEIVE_HIT_BY_PLAYER);
        result.add(Option.PLAYER_RECEIVE_HIT_GLOBAL);
        result.add(Option.PLAYER_DEATH);
        result.add(Option.ENTITY_DEATH);

        return result;
    }

    public static List<Option> getOptionWithDetailedItems() {
        List<Option> result = new ArrayList<>();
        result.add(Option.PLAYER_PICKUP_ITEM);
        result.add(Option.PLAYER_DROP_ITEM);
        result.add(Option.PLAYER_CONSUME);

        return result;
    }

    public static List<Option> getOptionWithCommand() {
        List<Option> result = new ArrayList<>();
        result.add(Option.PLAYER_WRITE_COMMAND);

        return result;
    }

    public static List<Option> getOptionWithDrops() {
        List<Option> result = new ArrayList<>();
        result.add(Option.ITEMSADDER_PLAYER_BLOCK_BREAK);
        result.add(Option.PLAYER_KILL_ENTITY);
        result.add(Option.PLAYER_KILL_PLAYER);
        result.add(Option.PLAYER_BLOCK_BREAK);
        result.add(Option.PLAYER_FISH_FISH);
        result.add(Option.ENTITY_DEATH);

        return result;
    }

    public static List<Option> getOptionWithDetailedClick() {
        List<Option> result = new ArrayList<>();
        result.add(Option.PLAYER_CLICK_ON_PLAYER);
        result.add(Option.PLAYER_CLICK_ON_ENTITY);

        return result;
    }

    public static List<Option> getOptionWithOnlyTypeClick() {
        List<Option> result = new ArrayList<>();
        result.add(Option.PLAYER_RIGHT_CLICK);
        result.add(Option.PLAYER_LEFT_CLICK);
        result.add(Option.PLAYER_ALL_CLICK);

        return result;
    }

    public static List<Option> getOptionResultOfProjectile() {
        List<Option> result = new ArrayList<>();

        return result;
    }

    public static List<SOption> getOptionWithTargetBlockSt() {
        List<SOption> result = new ArrayList<>();
        result.add(ITEMSADDER_PLAYER_BLOCK_BREAK);
        result.add(PLAYER_FILL_BUCKET);
        result.add(PLAYER_RIGHT_CLICK);
        result.add(PLAYER_LEFT_CLICK);
        result.add(PLAYER_ALL_CLICK);
        result.add(PLAYER_BLOCK_BREAK);
        result.add(PLAYER_BLOCK_PLACE);
        result.add(PLAYER_FERTILIZE_BLOCK);
        result.add(PLAYER_FISH_BLOCK);
        result.add(PLAYER_TRAMPLE_CROP);

        result.add(PLAYER_PROJECTILE_HIT_BLOCK);
        result.add(ENTITY_PROJECTILE_HIT_BLOCK);

        result.add(ENTITY_BREAK_DOOR);
        result.add(ENTITY_CHANGE_BLOCK);
        result.add(ENTITY_COMBUST_BY_BLOCK);
        result.add(ENTITY_DAMAGE_BY_BLOCK);
        result.add(ENTITY_ENTER_BLOCK);

        result.add(CROP_GROW);

        return result;
    }

    public static List<SOption> getOptionWithTargetEntitySt() {
        List<SOption> result = new ArrayList<>();
        result.add(Option.PLAYER_KILL_ENTITY);
        result.add(Option.PLAYER_CLICK_ON_ENTITY);
        result.add(Option.PLAYER_CUSTOM_LAUNCH);
        result.add(Option.PLAYER_RECEIVE_HIT_BY_ENTITY);
        result.add(Option.PLAYER_FISH_ENTITY);
        result.add(Option.PLAYER_FISH_FISH);
        result.add(Option.PLAYER_SHEAR_ENTITY);
        result.add(Option.PLAYER_MOUNT);
        result.add(Option.PLAYER_DISMOUNT);
        result.add(Option.PLAYER_TARGETED_BY_AN_ENTITY);
        result.add(Option.PLAYER_HIT_ENTITY);
        result.add(Option.PLAYER_PARTICIPATE_KILL_ENTITY);

        /* The entity is the blop of the fishhook */
        result.add(Option.PLAYER_FISH_NOTHING);

        result.add(Option.PLAYER_PROJECTILE_HIT_ENTITY);
        result.add(Option.ENTITY_PROJECTILE_HIT_ENTITY);

        result.add(Option.ENTITY_COMBUST_BY_ENTITY);
        result.add(Option.ENTITY_DAMAGE_BY_ENTITY);
        result.add(Option.ENTITY_PARTICIPATE_KILL_ENTITY);
        result.add(Option.ENTITY_TAME_BY_ENTITY);
        result.add(Option.ENTITY_TRANSFORM);

        return result;
    }

    public static List<SOption> getOptionWithTargetPlayerSt() {
        List<SOption> result = new ArrayList<>();
        result.add(Option.PLAYER_KILL_PLAYER);
        result.add(Option.PLAYER_CLICK_ON_PLAYER);
        result.add(Option.PLAYER_RECEIVE_HIT_BY_PLAYER);
        result.add(Option.PLAYER_FISH_PLAYER);
        result.add(Option.PLAYER_HIT_PLAYER);

        result.add(Option.PLAYER_PROJECTILE_HIT_PLAYER);
        result.add(Option.ENTITY_PROJECTILE_HIT_PLAYER);

        result.add(Option.ENTITY_PARTICIPATE_KILL_PLAYER);
        result.add(Option.PLAYER_PARTICIPATE_KILL_PLAYER);

        result.add(Option.ENTITY_TARGET_PLAYER);
        result.add(Option.ENTITY_DAMAGE_BY_PLAYER);
        result.add(Option.ENTITY_TAME_BY_PLAYER);

        return result;
    }

    public static List<SOption> getOptionWithEntitySt() {
        List<SOption> result = new ArrayList<>();

        result.add(Option.ENTITY_PARTICIPATE_KILL_PLAYER);
        result.add(Option.ENTITY_PARTICIPATE_KILL_ENTITY);

        result.add(Option.ENTITY_PROJECTILE_HIT_ENTITY);
        result.add(Option.ENTITY_PROJECTILE_HIT_PLAYER);
        result.add(Option.ENTITY_PROJECTILE_HIT_BLOCK);


        result.add(Option.ENTITY_SPAWN);
        result.add(Option.ENTITY_BREAK_DOOR);
        result.add(Option.ENTITY_BREED);
        result.add(Option.ENTITY_CHANGE_BLOCK);
        result.add(Option.ENTITY_COMBUST_BY_BLOCK);
        result.add(Option.ENTITY_COMBUST_BY_ENTITY);
        result.add(Option.ENTITY_DAMAGE_BY_PLAYER);
        result.add(Option.ENTITY_DAMAGE_BY_ENTITY);
        result.add(Option.ENTITY_DAMAGE_BY_BLOCK);
        result.add(Option.ENTITY_DROP_ITEM);
        result.add(Option.ENTITY_ENTER_BLOCK);
        result.add(Option.ENTITY_ENTER_LOVE_MODE);
        result.add(Option.ENTITY_EXPLODE);
        result.add(Option.ENTITY_PICKUP_ITEM);
        result.add(Option.ENTITY_PORTAL_ENTER);
        result.add(Option.ENTITY_PORTAL_EXIT);
        result.add(Option.ENTITY_REGAIN_HEALTH);
        result.add(Option.ENTITY_RESURRECT);
        result.add(Option.ENTITY_TAME_BY_PLAYER);
        result.add(Option.ENTITY_TAME_BY_ENTITY);
        result.add(Option.ENTITY_TARGET_PLAYER);
        result.add(Option.ENTITY_TARGET_ENTITY);
        result.add(Option.ENTITY_TELEPORT);
        result.add(Option.ENTITY_TRANSFORM);

        result.add(Option.ENDERDRAGON_CHANGE_PHASE);

        result.add(Option.ENTITY_DEATH);

        return result;
    }

    public static List<SOption> getOptionWithBlockSt() {
        List<SOption> result = new ArrayList<>();
        //TODO : Add all options with player
        return result;
    }

    public static List<SOption> getOptionWithPlayerSt() {
        List<SOption> result = new ArrayList<>();
        result.add(OptionGlobal.LOOP);

        result.add(Option.PLAYER_PARTICIPATE_KILL_PLAYER);
        result.add(Option.PLAYER_PARTICIPATE_KILL_ENTITY);

        result.add(Option.ITEMSADDER_PLAYER_BLOCK_BREAK);
        result.add(Option.PLAYER_ALL_CLICK);
        result.add(Option.PLAYER_ACTIVE_FLY);
        result.add(Option.PLAYER_ACTIVE_SNEAK);
        result.add(Option.PLAYER_ACTIVE_SPRINT);
        result.add(Option.PLAYER_BED_ENTER);
        result.add(Option.PLAYER_BED_LEAVE);
        result.add(Option.PLAYER_BEFORE_DEATH);
        result.add(Option.PLAYER_BLOCK_BREAK);
        result.add(Option.PLAYER_BLOCK_PLACE);
        result.add(Option.PLAYER_CHANGE_WORLD);
        result.add(Option.PLAYER_CLICK_ON_ENTITY);
        result.add(Option.PLAYER_CLICK_ON_PLAYER);
        result.add(Option.PLAYER_CONNECTION);
        result.add(Option.PLAYER_CONSUME);
        result.add(Option.PLAYER_CUSTOM_LAUNCH);
        result.add(Option.PLAYER_FOOD_CHANGE);
        result.add(Option.PLAYER_DEATH);
        result.add(Option.PLAYER_DISCONNECTION);
        result.add(Option.PLAYER_DISABLE_SNEAK);
        result.add(Option.PLAYER_DISABLE_SPRINT);
        result.add(Option.PLAYER_DISMOUNT);
        result.add(Option.PLAYER_DROP_ITEM);
        result.add(Option.PLAYER_EDIT_BOOK);
        result.add(Option.PLAYER_ENTER_IN_HIS_LAND);
        result.add(Option.PLAYER_ENTER_IN_HIS_PLOT);
        result.add(Option.PLAYER_FERTILIZE_BLOCK);
        result.add(Option.PLAYER_FILL_BUCKET);
        result.add(Option.PLAYER_FIRST_CONNECTION);
        result.add(Option.PLAYER_FISH_BLOCK);
        result.add(Option.PLAYER_FISH_ENTITY);
        result.add(Option.PLAYER_FISH_FISH);
        result.add(Option.PLAYER_FISH_NOTHING);
        result.add(Option.PLAYER_FISH_PLAYER);
        result.add(Option.PLAYER_HIT_ENTITY);
        result.add(Option.PLAYER_HIT_PLAYER);
        result.add(Option.PLAYER_ITEM_BREAK);
        result.add(Option.PLAYER_JUMP);
        result.add(Option.PLAYER_KILL_ENTITY);
        result.add(Option.PLAYER_KILL_PLAYER);
        result.add(Option.PLAYER_LEAVE_HIS_LAND);
        result.add(Option.PLAYER_LEAVE_HIS_PLOT);
        result.add(Option.PLAYER_LEFT_CLICK);
        result.add(Option.PLAYER_MOUNT);
        result.add(Option.PLAYER_WALK);
        result.add(Option.PLAYER_WRITE_COMMAND);
        result.add(Option.PLAYER_RECEIVE_HIT_BY_ENTITY);
        result.add(Option.PLAYER_RECEIVE_HIT_BY_PLAYER);
        result.add(Option.PLAYER_RECEIVE_HIT_GLOBAL);
        result.add(Option.PLAYER_RESPAWN);
        result.add(Option.PLAYER_RIGHT_CLICK);
        result.add(Option.PLAYER_SHEAR_ENTITY);
        result.add(Option.PLAYER_TARGETED_BY_AN_ENTITY);
        result.add(Option.PLAYER_TRAMPLE_CROP);
        result.add(Option.PLAYER_TELEPORT);
        result.add(Option.PLAYER_PICKUP_ITEM);

        result.add(Option.PLAYER_PROJECTILE_HIT_BLOCK);
        result.add(Option.PLAYER_PROJECTILE_HIT_PLAYER);
        result.add(Option.PLAYER_PROJECTILE_HIT_ENTITY);


        return result;
    }

    @Override
    public List<SOption> getPremiumOption() {
        return getPremiumOptionSt();
    }

    @Override
    public boolean isValidOption(String entry) {
        for (Option option : values()) {
            for (String name : option.getNames()) {
                if (name.equalsIgnoreCase(entry)) {
                    return true;
                }
            }
        }
        return SOption.super.isValidOption(entry);
    }

    @Override
    public SOption getOption(String entry) {
        for (Option option : values()) {
            for (String name : option.getNames()) {
                if (name.equalsIgnoreCase(entry)) {
                    return option;
                }
            }
        }
        return SOption.super.getOption(entry);
    }

    @Override
    public List<SOption> getValues() {
        List<SOption> result = SOption.super.getValues();
        result.addAll(Arrays.asList(Option.values()));
        return result;
    }

    @Override
    public SOption getDefaultValue() {
        return Option.PLAYER_ALL_CLICK;
    }

    public boolean containsThisName(String entry) {
        for (String name : getNames()) {
            if (name.equalsIgnoreCase(entry))
                return true;
        }
        return SOption.super.containsThisName(entry);
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    @Override
    public List<SOption> getOptionWithBlock() {
        List<SOption> result = SOption.super.getOptionWithBlock();
        result.addAll(getOptionWithBlockSt());
        return result;
    }


    @Override
    public List<SOption> getOptionWithOwner() {
        List<SOption> result = SOption.super.getOptionWithOwner();
        return result;
    }

    @Override
    public List<SOption> getOptionWithPlayer() {
        List<SOption> result = SOption.super.getOptionWithPlayer();
        result.addAll(getOptionWithPlayerSt());
        return result;
    }

    @Override
    public List<SOption> getOptionWithEntity() {
        List<SOption> result = SOption.super.getOptionWithEntity();
        result.addAll(getOptionWithEntitySt());
        return result;
    }

    @Override
    public List<SOption> getOptionWithTargetBlock() {
        List<SOption> result = SOption.super.getOptionWithTargetBlock();
        result.addAll(getOptionWithTargetBlockSt());
        return result;
    }

    @Override
    public List<SOption> getOptionWithTargetEntity() {
        List<SOption> result = SOption.super.getOptionWithTargetEntity();
        result.addAll(getOptionWithTargetEntitySt());
        return result;
    }

    @Override
    public List<SOption> getOptionWithTargetPlayer() {
        List<SOption> result = SOption.super.getOptionWithTargetPlayer();
        result.addAll(getOptionWithTargetPlayerSt());
        return result;
    }

    @Override
    public List<SOption> getOptionWithWorld() {
        List<SOption> result = SOption.super.getOptionWithWorld();
        result.addAll(getValues());
        return result;
    }

    @Override
    public List<SOption> getOptionWithItem() {
        List<SOption> result = SOption.super.getOptionWithItem();
        return result;
    }

    @Override
    public boolean isLoopOption() {
        return SOption.super.isLoopOption();
    }

}